package hu.sch.kfc.client.atmosphere;

import hu.sch.kfc.client.event.LikeEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.rpc.StatusCodeException;

public class AtmosphereClient {

    private final HandlerManager eventBus;

    public AtmosphereClient(HandlerManager eventBus) {
        this.eventBus = eventBus;
        final AtmosphereTransport at = GWT.create(AtmosphereTransport.class);
        at.setCallback(new AtmosphereCallback() {
            
            @Override
            public void onError(StatusCodeException statusCodeException, boolean connected) {
            }
            
            @Override
            public void onError(RequestException requestException, boolean connected) {
            }
            
            @Override
            public void onDisconnected() {
                // ha vége a kapcsolatnak, akkor induljon megint.
                at.start();
            }

            @Override
            public void onMessage() {
                AtmosphereClient.this.eventBus.fireEvent(new LikeEvent());
            }
        });
        
        at.start();
    }
    
}
