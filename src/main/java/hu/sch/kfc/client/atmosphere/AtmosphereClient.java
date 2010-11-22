package hu.sch.kfc.client.atmosphere;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.rpc.StatusCodeException;

public class AtmosphereClient {

    private final EventBus eventBus;

    public AtmosphereClient(EventBus eventBus) {
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
            public void onEvent(GwtEvent<?> e) {
                AtmosphereClient.this.eventBus.fireEvent(e);
            }
        });
        
        at.start();
    }
    
}
