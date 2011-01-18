package hu.sch.kfc.server;

import hu.sch.kfc.client.service.EventService;
import hu.sch.kfc.shared.event.LikeEvent;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.annotation.Suspend.SCOPE;
import org.atmosphere.cpr.DefaultBroadcaster;
import org.atmosphere.jersey.Broadcastable;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;
import com.google.gwt.user.server.rpc.SerializationPolicyProvider;
import com.sun.jersey.spi.resource.Singleton;

@Path("/")
@Produces("text/plain;charset=UTF-8")
@Singleton
public class JerseyTest implements SerializationPolicyProvider, EventService {

    @Context
    HttpServletRequest servletRequest;
    @Context
    ServletContext context;

    int liked = 0;
    
    @GET
    @Suspend(scope = SCOPE.APPLICATION, listeners = EventsLogger.class, period = 30, timeUnit = TimeUnit.SECONDS, outputComments = true)
    public String subscribe(@Context DefaultBroadcaster bc) {
        // bc.broadcast("");
        // bc.getBroadcasterConfig().setBroadcasterCache(new HeaderBroadcasterCache());
        return "";
    }

    @POST
    @Broadcast
    public Broadcastable publish(@Context DefaultBroadcaster bc) {
        String encoded = null;
        StringBuilder sb = new StringBuilder(100);
        try {
            Method method = this.getClass().getMethod("getEvent", (Class<?>) null);
            encoded = RPC.invokeAndEncodeResponse(this, method, null/*, getSerializationPolicy(
                    "http://127.0.0.1:8888/kfc/", "DD98F5EAEFEC65CD76A0D7BC1E3C8E8D")*/);
            sb.append(encoded).append('\n');
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SerializationException e) {
            e.printStackTrace();
        }
        return new Broadcastable(sb.toString(), "", bc);
    }

    @Override
    public SerializationPolicy getSerializationPolicy(String moduleBaseURL, String strongName) {
        String modulePath = null;
        if (moduleBaseURL != null) {
            try {
                modulePath = new URL(moduleBaseURL).getPath();
            } catch (MalformedURLException ex) {
                System.err.println("Malformed moduleBaseURL: " + moduleBaseURL);
            }
        }

        SerializationPolicy serializationPolicy = null;

        String contextPath = servletRequest.getContextPath();
        String contextRelativePath = modulePath.substring(contextPath.length());
        String serializationPolicyFilePath = SerializationPolicyLoader
                .getSerializationPolicyFileName(contextRelativePath + strongName);
        System.err.println(serializationPolicyFilePath);

        InputStream is = context.getResourceAsStream(serializationPolicyFilePath);
        if (is != null) {
            try {
                serializationPolicy = SerializationPolicyLoader.loadFromStream(is, null);
            } catch (ParseException e) {
                e.printStackTrace(System.err);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }

        return serializationPolicy;
    }

    @Override
    public GwtEvent<?> getEvent() {
        return new LikeEvent(liked += 2);
    }
}
