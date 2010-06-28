package hu.sch.kfc.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListener;

public class EventsLogger implements AtmosphereResourceEventListener {

    public EventsLogger() {
    }

    @Override
    public void onSuspend(final AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event){
        System.err.println("onSuspend: " + event.getResource().getRequest().getRemoteAddr()
                + event.getResource().getRequest().getRemotePort());
    }
    @Override
    public void onResume(AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
        System.err.println("onResume: " + event.getResource().getRequest().getRemoteAddr());
    }
    @Override
    public void onDisconnect(AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
        System.err.println("onDisconnect: " + event.getResource().getRequest().getRemoteAddr()
                + event.getResource().getRequest().getRemotePort());
    }
    @Override
    public void onBroadcast(AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
        System.err.println("onBroadcast: " + event.getMessage());
    }
}
