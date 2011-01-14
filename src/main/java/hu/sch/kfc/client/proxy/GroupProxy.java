package hu.sch.kfc.client.proxy;

import hu.sch.kfc.server.domain.Group;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Group.class)
public interface GroupProxy extends EntityProxy {

    String getToken();

    void setToken(String token);

    String getName();

    void setName(String name);

    EntityProxyId<GroupProxy> stableId();
}
