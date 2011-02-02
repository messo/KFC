package hu.sch.kfc.client.atmosphere.impl;

import hu.sch.kfc.client.atmosphere.AtmosphereTransport;
import com.google.gwt.xhr.client.XMLHttpRequest;

/**
 * IE specifikus implementációja a {@link AtmosphereTransport}-nak.
 * 
 * @author	messo
 * @since   0.1
 */
public class AtmosphereTransportImplIE extends AtmosphereTransport {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addRequestHeaders() {
        // Internet Explorer esetében nem jó a streaming, maradjunk a pollingnál
        xmlHttpRequest.setRequestHeader(X_ATMOSPHERE_TRANSPORT, TRANSPORT_POLLING);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onXMLHttpRequestReadyStateChange(XMLHttpRequest request) {
        // Mivel nincs streaming, ezért ne is nézzük, hogy LOADING-e vagy sem, csak akkor
        // cselekszünk, ha már megjött a kérés.
        if (!aborted) {
            if (request.getReadyState() == XMLHttpRequest.DONE) {
                onLoaded(request.getStatus(), request.getResponseText());
            }
        }
    }
}
