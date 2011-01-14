package hu.sch.kfc.client.request;

import java.util.List;
import hu.sch.kfc.client.proxy.ProgramProxy;
import hu.sch.kfc.server.domain.Program;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Program.class)
public interface ProgramRequest extends RequestContext {

    Request<List<ProgramProxy>> findProgramsByGroupToken(String groupToken);
}
