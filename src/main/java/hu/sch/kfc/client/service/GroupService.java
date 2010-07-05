package hu.sch.kfc.client.service;

import hu.sch.kfc.client.cache.CachingRemoteService;
import hu.sch.kfc.client.cache.UseCache;
import hu.sch.kfc.shared.Group;
import java.util.List;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("group")
public interface GroupService extends CachingRemoteService {

    List<Group> getGroups();

    @UseCache
    Group getGroupByToken(String token);
}
