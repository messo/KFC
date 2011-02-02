package hu.sch.kfc.server;

import javax.naming.InitialContext;
import com.google.gwt.requestfactory.shared.ServiceLocator;

public class BeanLocator implements ServiceLocator {

    @Override
    public Object getInstance(Class<?> clazz) {
        return lookupBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T lookupBean(Class<T> clazz) {
        try {
            return (T) InitialContext.doLookup("java:module/" + clazz.getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
