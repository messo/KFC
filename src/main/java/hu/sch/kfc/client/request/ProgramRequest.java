package hu.sch.kfc.client.request;

import java.util.List;
import hu.sch.kfc.client.model.ProgramProxy;
import hu.sch.kfc.server.domain.Program;
import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Program.class)
public interface ProgramRequest extends RequestContext {

    Request<List<ProgramProxy>> findProgramsByGroupToken(String groupToken);

    Request<ProgramProxy> findProgram(Long programId);

    InstanceRequest<ProgramProxy, Void> persist();
}
