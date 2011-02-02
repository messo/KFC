package hu.sch.kfc.client.atmosphere;

import hu.sch.kfc.client.service.EventService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.google.gwt.xhr.client.ReadyStateChangeHandler;
import com.google.gwt.xhr.client.XMLHttpRequest;

public class AtmosphereTransport {

    protected static final String ATMOSPHERE_VERSION = "0.6";
    protected static final String X_ATMOSPHERE_TRANSPORT = "X-Atmosphere-Transport";
    protected static final String TRANSPORT_STREAMING = "streaming";
    protected static final String TRANSPORT_POLLING = "long-polling";

    protected XMLHttpRequest xmlHttpRequest;
    protected AtmosphereCallback callback;
    protected boolean aborted = false;
    protected boolean expectingDisconnection = false;
    protected int readedSofar = 0;
    protected static final SerializationStreamFactory ssf = GWT.create(EventService.class);

    private static native String getDate() /*-{
        return new Date().toString();
    }-*/;

    public AtmosphereTransport() {
    }

    public void setCallback(AtmosphereCallback callback) {
        this.callback = callback;
    }

    public void start() {
        reset();
        xmlHttpRequest = XMLHttpRequest.create();
        try {
            xmlHttpRequest.open("GET", GWT.getModuleBaseURL() + "channel");
            xmlHttpRequest.setRequestHeader("Accept", "text/plain");
            xmlHttpRequest.setRequestHeader("X-Atmosphere-Version", ATMOSPHERE_VERSION);
            // xmlHttpRequest.setRequestHeader("X-Cache-Date", getDate());
            addRequestHeaders();
            xmlHttpRequest.setOnReadyStateChange(new ReadyStateChangeHandler() {

                @Override
                public void onReadyStateChange(XMLHttpRequest request) {
                    onXMLHttpRequestReadyStateChange(request);
                }
            });
            xmlHttpRequest.send();
        } catch (JavaScriptException e) {
            xmlHttpRequest = null;
            callback.onError(new RequestException(e.getMessage()), false);
        }
    }

    private void reset() {
        aborted = false;
        expectingDisconnection = false;
        readedSofar = 0;
    }

    public void disconnect() {
        aborted = true;
        expectingDisconnection = true;
        if (xmlHttpRequest != null) {
            xmlHttpRequest.clearOnReadyStateChange();
            xmlHttpRequest.abort();
            xmlHttpRequest = null;
        }
    }

    protected void onXMLHttpRequestReadyStateChange(XMLHttpRequest request) {
        if (!aborted) {
            switch (request.getReadyState()) {
            case XMLHttpRequest.LOADING:
                onReceiving(request.getStatus(), request.getResponseText());
                break;
            case XMLHttpRequest.DONE:
                onLoaded(request.getStatus(), request.getResponseText());
                break;
            }
        }
    }

    protected void addRequestHeaders() {
        xmlHttpRequest.setRequestHeader(X_ATMOSPHERE_TRANSPORT, TRANSPORT_STREAMING);
    }

    protected void onLoaded(int statusCode, String responseText) {
        xmlHttpRequest.clearOnReadyStateChange();
        xmlHttpRequest = null;
        onReceiving(statusCode, responseText, false);
    }

    protected void onReceiving(int statusCode, String responseText) {
        onReceiving(statusCode, responseText, true);
    }

    protected void onReceiving(int statusCode, String responseText, boolean connected) {
        if (statusCode != Response.SC_OK) {
            if (!connected) {
                expectingDisconnection = true;
                callback.onError(new StatusCodeException(statusCode, responseText), connected);
            }
        } else {
            String newMessage = responseText.substring(readedSofar);
            if (!newMessage.startsWith("<!--") && newMessage.length() != 0) {
                String[] messages = newMessage.split("/\n/");
                for (String message : messages) {
                    try {
                        GwtEvent<?> event = (GwtEvent<?>) ssf.createStreamReader(message)
                                .readObject();
                        callback.onEvent(event);
                    } catch (SerializationException e) {
                        GWT.log(e.getClass().getName(), e);
                    }
                }
            }
            readedSofar = responseText.length();

            if (!connected) {
                callback.onDisconnected();
                /*
                 * if (expectingDisconnection) { } else { GWT.log("ERROR!!!!"); //
                 * callback.onError(new CometException("Unexpected disconnection"), false); }
                 */
            }
        }
    }
}
