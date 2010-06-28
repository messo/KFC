package hu.sch.kfc.client.service;

import hu.sch.kfc.shared.Program;
import hu.sch.kfc.shared.Group;
import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("program")
public interface ProgramService extends RemoteService {

    List<Program> getEventsForGroup(Group g);
    List<Program> getEventsForGroupToken(String token);
}
