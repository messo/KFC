package hu.sch.kfc.shared.proxy;

import hu.sch.kfc.domain.Program;
import hu.sch.kfc.server.EntityLocator;
import java.util.List;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Program.class, locator = EntityLocator.class)
public interface ProgramProxy extends EntityProxy {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    List<OrderIntervalProxy> getOrderIntervals();
    
    void setOrderIntervals(List<OrderIntervalProxy> list);
    
    DateIntervalProxy getOrderInterval();

    String getGroupToken();

    EntityProxyId<ProgramProxy> stableId();
}
