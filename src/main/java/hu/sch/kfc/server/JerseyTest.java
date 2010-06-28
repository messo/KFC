package hu.sch.kfc.server;

import java.util.concurrent.TimeUnit;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.annotation.Suspend.SCOPE;
import org.atmosphere.cache.HeaderBroadcasterCache;
import org.atmosphere.cpr.DefaultBroadcaster;
import org.atmosphere.jersey.Broadcastable;

@Path("/")
@Produces("text/plain;charset=UTF-8")
public class JerseyTest {

    @GET
    @Suspend(scope = SCOPE.APPLICATION, listeners = EventsLogger.class, period = 30, timeUnit = TimeUnit.SECONDS, outputComments = false)
    public String subscribe(@Context DefaultBroadcaster bc) {
        //bc.broadcast("");
        //bc.getBroadcasterConfig().setBroadcasterCache(new HeaderBroadcasterCache());
        return "<!--comment-->";
    }

    @POST
    @Broadcast
    public Broadcastable publish(@Context DefaultBroadcaster bc,
            @QueryParam("message") String message) {
        return new Broadcastable(message, "", bc);
    }
}
