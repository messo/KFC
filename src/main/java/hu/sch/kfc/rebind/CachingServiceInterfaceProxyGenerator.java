package hu.sch.kfc.rebind;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.user.rebind.rpc.CachingProxyCreator;
import com.google.gwt.user.rebind.rpc.ProxyCreator;
import com.google.gwt.user.rebind.rpc.ServiceInterfaceProxyGenerator;

public class CachingServiceInterfaceProxyGenerator extends ServiceInterfaceProxyGenerator {

    @Override
    protected ProxyCreator createProxyCreator(JClassType remoteService) {
        return new CachingProxyCreator(remoteService);
    }
    
}
