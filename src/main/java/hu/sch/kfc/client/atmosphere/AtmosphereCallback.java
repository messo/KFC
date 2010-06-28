package hu.sch.kfc.client.atmosphere;

import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.rpc.StatusCodeException;

interface AtmosphereCallback {

    void onMessage();

    void onError(RequestException requestException, boolean connected);

    void onError(StatusCodeException statusCodeException, boolean connected);

    void onDisconnected();
}
