package hu.sch.kfc.client.request;

import com.google.gwt.requestfactory.shared.RequestFactory;

public interface KFCRequestFactory extends RequestFactory {

    GroupRequest groupRequest();

    ProgramRequest programRequest();
}
