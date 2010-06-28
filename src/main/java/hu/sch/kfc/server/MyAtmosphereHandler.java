package hu.sch.kfc.server;

import hu.sch.kfc.client.service.EventService;
import hu.sch.kfc.shared.Event;
import hu.sch.kfc.shared.LikeEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;

public class MyAtmosphereHandler extends AbstractReflectorAtmosphereHandler {

    private AtomicInteger counter = new AtomicInteger(0);

    public MyAtmosphereHandler() {
    }

    /**
     * Egy Event típusú objektumot szerializálunk GWT-RPC szerint.
     * 
     * @param e az Event, amit át akarunk küldeni a kliensnek
     * @return szerializált objektum
     */
    private static String encodeEvent(Event e) {
        Method method = null;
        try {
            method = EventService.class.getMethod("getEvent", null);
        } catch (SecurityException ex) {
            ex.printStackTrace(System.err);
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace(System.err);
        }
        String result = null;
        try {
            result = RPC.encodeResponseForSuccess(method, e);
        } catch (SerializationException ex) {
            ex.printStackTrace(System.err);
        }
        return result;
    }

    public void onRequest(
            AtmosphereResource<HttpServletRequest, HttpServletResponse> atmoResource)
            throws IOException {
        HttpServletRequest request = atmoResource.getRequest();
        HttpServletResponse response = atmoResource.getResponse();

        // atmoResource.getBroadcaster().getBroadcasterConfig()
        // .setBroadcasterCache(new SessionBroadcasterCache());

        if (request.getMethod().equalsIgnoreCase("GET")) {
            // Ha GET kérés érkezik, akkor suspend.
            atmoResource.suspend(30 * 1000, false);
        } else {
            Event e = new LikeEvent(counter.incrementAndGet());
            atmoResource.getBroadcaster().broadcast(encodeEvent(e));
        }
    }

    @Override
    public void onStateChange(
            AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> atmoResEvent)
            throws IOException {

        HttpServletResponse res = atmoResEvent.getResource().getResponse();

        if (atmoResEvent.isResumedOnTimeout()) {
            res.getWriter().write("TIMEOUT!");
        } else {
            res.getWriter().write((String) atmoResEvent.getMessage());
            res.getWriter().flush();
            atmoResEvent.getResource().resume();
        }
    }
}
