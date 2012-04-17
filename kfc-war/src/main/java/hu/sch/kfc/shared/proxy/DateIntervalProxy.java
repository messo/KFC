package hu.sch.kfc.shared.proxy;

import hu.sch.kfc.misc.DateInterval;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyFor(DateInterval.class)
public interface DateIntervalProxy extends ValueProxy {

    String getInterval();

    boolean getIsEnded(); // TODO - támogatnia kéne isEnded()-et, de úgy tűnik még nem az igazi...
}
