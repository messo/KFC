package hu.sch.kfc.client.service;

import hu.sch.kfc.shared.Group;
import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("group")
public interface GroupService extends RemoteService {

    List<Group> getGroups();

    Group getGroupByToken(String token);
}
