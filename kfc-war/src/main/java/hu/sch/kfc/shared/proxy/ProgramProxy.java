package hu.sch.kfc.shared.proxy;

import java.util.List;
import hu.sch.kfc.domain.Program;
import hu.sch.kfc.server.EntityLocator;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(value = Program.class, locator = EntityLocator.class)
public interface ProgramProxy extends EntityProxy {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    List<OrderIntervalProxy> getOrderIntervals();
    
    void setOrderIntervals(List<DateIntervalProxy> list);
    
    DateIntervalProxy getOrderInterval();

    String getGroupToken();

    EntityProxyId<ProgramProxy> stableId();
}
