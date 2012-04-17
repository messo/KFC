package hu.sch.kfc.shared.service;

import hu.sch.kfc.ejb.ProgramManager;
import hu.sch.kfc.server.BeanLocator;
import hu.sch.kfc.shared.proxy.ProgramProxy;
import java.util.List;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = ProgramManager.class, locator = BeanLocator.class)
public interface ProgramRequestContext extends RequestContext {

    Request<List<ProgramProxy>> findProgramsByGroupToken(String groupToken);

    Request<ProgramProxy> findProgram(Long programId);

    Request<Void> persist(ProgramProxy program);
}
