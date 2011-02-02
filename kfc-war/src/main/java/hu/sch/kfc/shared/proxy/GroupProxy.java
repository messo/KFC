package hu.sch.kfc.shared.proxy;

import hu.sch.kfc.domain.Group;
import hu.sch.kfc.server.EntityLocator;
import java.util.List;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(value = Group.class, locator = EntityLocator.class)
public interface GroupProxy extends EntityProxy {

    String getToken();

    void setToken(String token);

    String getName();

    void setName(String name);

    List<ProgramProxy> getPrograms();

    EntityProxyId<GroupProxy> stableId();
}
