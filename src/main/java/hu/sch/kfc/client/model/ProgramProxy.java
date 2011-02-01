package hu.sch.kfc.client.model;

import java.util.List;
import hu.sch.kfc.server.domain.Program;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Program.class)
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
