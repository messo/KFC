package hu.sch.kfc.client.model;

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

    DateIntervalProxy getOrderInterval();
    
    EntityProxyId<ProgramProxy> stableId();
}
