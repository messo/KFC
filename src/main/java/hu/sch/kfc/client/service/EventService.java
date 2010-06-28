package hu.sch.kfc.client.service;

import hu.sch.kfc.shared.Event;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("atmosphere")
public interface EventService extends RemoteService {

    Event getEvent();
}
