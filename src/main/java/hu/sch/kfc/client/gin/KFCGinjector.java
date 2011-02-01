package hu.sch.kfc.client.gin;

import hu.sch.kfc.client.Application;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ KFCGinModule.class })
public interface KFCGinjector extends Ginjector {

    Application getApplication();
}
