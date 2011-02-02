package hu.sch.kfc.shared.proxy;

import hu.sch.kfc.misc.DateInterval;
import com.google.gwt.requestfactory.shared.ProxyFor;
import com.google.gwt.requestfactory.shared.ValueProxy;

@ProxyFor(DateInterval.class)
public interface DateIntervalProxy extends ValueProxy {

    String getInterval();

    boolean getIsEnded(); // TODO - támogatnia kéne isEnded()-et, de úgy tűnik még nem az igazi...
}
