package hu.sch.kfc.shared.proxy;

import java.util.Date;
import hu.sch.kfc.domain.OrderInterval;
import hu.sch.kfc.server.EntityLocator;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(value = OrderInterval.class, locator = EntityLocator.class)
public interface OrderIntervalProxy extends EntityProxy {

    Date getStart();

    void setStart(Date start);

    Date getEnd();

    void setEnd(Date end);

    Integer getCount();

    void setCount(Integer count);
}
