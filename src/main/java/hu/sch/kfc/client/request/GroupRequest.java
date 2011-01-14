package hu.sch.kfc.client.request;

import hu.sch.kfc.client.model.GroupProxy;
import hu.sch.kfc.server.domain.Group;
import java.util.List;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Group.class)
public interface GroupRequest extends RequestContext {

    Request<GroupProxy> findGroup(Long id);

    Request<GroupProxy> findGroupByToken(String groupToken);

    Request<List<GroupProxy>> findGroups();
}
