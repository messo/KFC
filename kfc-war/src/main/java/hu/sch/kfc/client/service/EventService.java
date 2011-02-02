package hu.sch.kfc.client.service;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("event")
public interface EventService extends RemoteService {

    GwtEvent<?> getEvent();
}
