package hu.sch.kfc.server;

import hu.sch.kfc.client.service.EventService;
import hu.sch.kfc.shared.event.LikeEvent;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
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
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.sun.jersey.spi.resource.Singleton;

@Path("/")
@Produces("text/plain;charset=UTF-8")
@Singleton
public class JerseyTest {

    int liked = 0;
    
    @GET
    @Suspend(scope = SCOPE.APPLICATION, listeners = EventsLogger.class, period = 30, timeUnit = TimeUnit.SECONDS, outputComments = true)
    public String subscribe(@Context DefaultBroadcaster bc) {
        //bc.broadcast("");
        //bc.getBroadcasterConfig().setBroadcasterCache(new HeaderBroadcasterCache());
        return "";
    }

    @POST
    @Broadcast
    public Broadcastable publish(@Context DefaultBroadcaster bc) {
        LikeEvent event = new LikeEvent(++liked);
        String encoded = null;
        StringBuilder sb = new StringBuilder(100);
        try {
            Method serviceMethod = EventService.class.getMethod("getEvent", null);
            encoded = RPC.encodeResponseForSuccess(serviceMethod, event);
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
}
