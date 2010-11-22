/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.user.rebind.rpc;

import hu.sch.kfc.client.cache.Cache;
import hu.sch.kfc.client.cache.Cacheable;
import hu.sch.kfc.client.cache.UseCache;
import java.util.HashMap;
import java.util.Map;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.dev.generator.NameFactory;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.FailedRequest;
import com.google.gwt.user.client.rpc.impl.FailingRequestBuilder;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Creates a client-side proxy for a {@link hu.sch.kfc.client.cache.CachingRemoteService
 * CachingRemoteService} interface as well as the necessary type and field serializers.
 * ProxyCreator-ból, amit kellett azt copyztuk.
 * 
 * @version 2.1.0
 */
public class CachingProxyCreator extends ProxyCreator {

    private static final Map<JPrimitiveType, ResponseReader> JPRIMITIVETYPE_TO_RESPONSEREADER = new HashMap<JPrimitiveType, ResponseReader>();

    public CachingProxyCreator(JClassType serviceIntf) {
        super(serviceIntf);
    }

    /**
     * Generates the client's asynchronous proxy method.
     * 
     * @param serializableTypeOracle
     *            the type oracle
     */
    protected void generateProxyMethod(SourceWriter w,
            SerializableTypeOracle serializableTypeOracle, JMethod syncMethod, JMethod asyncMethod) {

        w.println();

        // Write the method signature
        JType asyncReturnType = asyncMethod.getReturnType().getErasedType();
        w.print("public ");
        w.print(asyncReturnType.getQualifiedSourceName());
        w.print(" ");
        w.print(asyncMethod.getName() + "(");

        boolean needsComma = false;
        NameFactory nameFactory = new NameFactory();
        JParameter[] asyncParams = asyncMethod.getParameters();
        for (int i = 0; i < asyncParams.length; ++i) {
            JParameter param = asyncParams[i];

            if (needsComma) {
                w.print(", ");
            } else {
                needsComma = true;
            }

            /*
             * Ignoring the AsyncCallback parameter, if any method requires a call to
             * SerializationStreamWriter.writeObject we need a try catch block
             */
            JType paramType = param.getType();
            paramType = paramType.getErasedType();

            w.print(paramType.getQualifiedSourceName());
            w.print(" ");

            String paramName = param.getName();
            nameFactory.addName(paramName);
            w.print(paramName);
        }

        w.println(") {");
        w.indent();

        // Ide jön a tuning.
        // Nézzük meg a visszatérési értéket
        JClassType ct = syncMethod.getReturnType().isClass();
        // ha van visszatérési érték, és meg van annotálva a @UseCache-el,
        // akkor jók vagyunk
        if (ct != null && syncMethod.getAnnotation(UseCache.class) != null) {
            JClassType[] intfs = ct.getImplementedInterfaces();
            for (JClassType intf : intfs) {
                // ha az implementált interfészek között van a Cacheable, akkor okés
                // rakjuk be a Cache logikát :)
                if (intf.getQualifiedSourceName().equals(Cacheable.class.getCanonicalName())) {
                    w.println("// Cache ellenőrzése");
                    w.print(ct.getQualifiedSourceName());
                    w.print(" obj = ");
                    w.print(Cache.class.getCanonicalName());
                    w.println(".get(" + ct.getQualifiedSourceName() + ".class, "
                            + syncMethod.getParameters()[0].getName() + ");");
                    w.println("if( obj != null ) {");
                    w.indent();
                    w.println(asyncParams[asyncParams.length - 1].getName() + ".onSuccess(obj);");
                    w.println("return ;");
                    w.outdent();
                    w.println("}");
                    w.println();
                    break;
                }
            }
        }

        String statsContextName = nameFactory.createName("statsContext");
        generateRpcStatsContext(w, syncMethod, asyncMethod, statsContextName);

        String statsMethodExpr = getProxySimpleName() + "." + syncMethod.getName();
        String tossName = nameFactory.createName("toss");
        w.println(
                "boolean %s = %s.isStatsAvailable() && %s.stats(%s.timeStat(\"%s\", \"begin\"));",
                tossName, statsContextName, statsContextName, statsContextName, statsMethodExpr);
        w.print(SerializationStreamWriter.class.getSimpleName());
        w.print(" ");
        String streamWriterName = nameFactory.createName("streamWriter");
        w.println(streamWriterName + " = createStreamWriter();");
        w.println("// createStreamWriter() prepared the stream");
        w.println("try {");
        w.indent();

        w.println(streamWriterName + ".writeString(REMOTE_SERVICE_INTERFACE_NAME);");

        // Write the method name
        w.println(streamWriterName + ".writeString(\"" + syncMethod.getName() + "\");");

        // Write the parameter count followed by the parameter values
        JParameter[] syncParams = syncMethod.getParameters();
        w.println(streamWriterName + ".writeInt(" + syncParams.length + ");");
        for (JParameter param : syncParams) {
            JType paramType = param.getType().getErasedType();
            String typeNameExpression = computeTypeNameExpression(paramType);
            assert typeNameExpression != null : "Could not compute a type name for "
                    + paramType.getQualifiedSourceName();
            w.println(streamWriterName + ".writeString(" + typeNameExpression + ");");
        }

        // Encode all of the arguments to the asynchronous method, but exclude the
        // last argument which is the callback instance.
        //
        for (int i = 0; i < asyncParams.length - 1; ++i) {
            JParameter asyncParam = asyncParams[i];
            w.print(streamWriterName + ".");
            w.print(Shared.getStreamWriteMethodNameFor(asyncParam.getType()));
            w.println("(" + asyncParam.getName() + ");");
        }

        String payloadName = nameFactory.createName("payload");
        w.println("String " + payloadName + " = " + streamWriterName + ".toString();");

        w.println(tossName + " = " + statsContextName + ".isStatsAvailable() && "
                + statsContextName + ".stats(" + statsContextName + ".timeStat(\""
                + statsMethodExpr + "\",  \"requestSerialized\"));");

        /*
         * Depending on the return type for the async method, return a RequestBuilder, a Request, or
         * nothing at all.
         */
        if (asyncReturnType == JPrimitiveType.VOID) {
            w.print("doInvoke(");
        } else if (asyncReturnType.getQualifiedSourceName().equals(RequestBuilder.class.getName())) {
            w.print("return doPrepareRequestBuilder(");
        } else if (asyncReturnType.getQualifiedSourceName().equals(Request.class.getName())) {
            w.print("return doInvoke(");
        } else {
            // This method should have been caught by RemoteServiceAsyncValidator
            throw new RuntimeException("Unhandled return type "
                    + asyncReturnType.getQualifiedSourceName());
        }

        JParameter callbackParam = asyncParams[asyncParams.length - 1];
        String callbackName = callbackParam.getName();
        JType returnType = syncMethod.getReturnType();
        w.print("ResponseReader." + getResponseReaderFor(returnType).name());
        w.println(", \"" + getProxySimpleName() + "." + syncMethod.getName() + "\", "
                + statsContextName + ", " + payloadName + ", " + callbackName + ");");

        w.outdent();
        w.print("} catch (SerializationException ");
        String exceptionName = nameFactory.createName("ex");
        w.println(exceptionName + ") {");
        w.indent();
        if (!asyncReturnType.getQualifiedSourceName().equals(RequestBuilder.class.getName())) {
            /*
             * If the method returns void or Request, signal the serialization error immediately. If
             * the method returns RequestBuilder, the error will be signaled whenever
             * RequestBuilder.send() is invoked.
             */
            w.println(callbackName + ".onFailure(" + exceptionName + ");");
        }
        if (asyncReturnType.getQualifiedSourceName().equals(RequestBuilder.class.getName())) {
            w.println("return new " + FailingRequestBuilder.class.getName() + "(" + exceptionName
                    + ", " + callbackName + ");");
        } else if (asyncReturnType.getQualifiedSourceName().equals(Request.class.getName())) {
            w.println("return new " + FailedRequest.class.getName() + "();");
        } else {
            assert asyncReturnType == JPrimitiveType.VOID;
        }
        w.outdent();
        w.println("}");

        w.outdent();
        w.println("}");
    }

    private ResponseReader getResponseReaderFor(JType returnType) {
        if (returnType.isPrimitive() != null) {
            return JPRIMITIVETYPE_TO_RESPONSEREADER.get(returnType.isPrimitive());
        }

        if (returnType.getQualifiedSourceName().equals(String.class.getCanonicalName())) {
            return ResponseReader.STRING;
        }

        return ResponseReader.OBJECT;
    }
}
