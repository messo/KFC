package hu.sch.kfc.shared.service;

import com.google.gwt.requestfactory.shared.RequestFactory;

public interface KFCRequestFactory extends RequestFactory {

    GroupRequestContext groupRequest();

    ProgramRequestContext programRequest();
}
