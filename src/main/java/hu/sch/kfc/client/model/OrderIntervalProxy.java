package hu.sch.kfc.client.model;

import java.util.Date;
import hu.sch.kfc.server.domain.OrderInterval;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(OrderInterval.class)
public interface OrderIntervalProxy extends EntityProxy {

    Date getStart();

    void setStart(Date start);

    Date getEnd();

    void setEnd(Date end);

    Integer getCount();

    void setCount(Integer count);
}
