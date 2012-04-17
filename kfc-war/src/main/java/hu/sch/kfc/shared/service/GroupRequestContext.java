package hu.sch.kfc.shared.service;

import hu.sch.kfc.ejb.GroupManager;
import hu.sch.kfc.server.BeanLocator;
import hu.sch.kfc.shared.proxy.GroupProxy;
import java.util.List;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = GroupManager.class, locator = BeanLocator.class)
public interface GroupRequestContext extends RequestContext {

    Request<GroupProxy> findGroup(Long id);

    Request<GroupProxy> findGroupByToken(String groupToken);

    Request<List<GroupProxy>> findGroups();
}
