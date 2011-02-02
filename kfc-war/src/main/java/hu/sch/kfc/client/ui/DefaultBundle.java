package hu.sch.kfc.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface DefaultBundle extends ClientBundle {

    public static final DefaultBundle INSTANCE = GWT.create(DefaultBundle.class);

    @Source("default.css")
    DefaultStyle defStyle();
}
