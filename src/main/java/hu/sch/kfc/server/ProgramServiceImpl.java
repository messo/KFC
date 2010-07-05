package hu.sch.kfc.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import hu.sch.kfc.client.service.ProgramService;
import hu.sch.kfc.server.domain.GroupEntity;
import hu.sch.kfc.server.domain.ProgramEntity;
import hu.sch.kfc.shared.Program;
import hu.sch.kfc.shared.Group;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProgramServiceImpl extends RemoteServiceServlet implements ProgramService {

    private static final EntityManager em = EMF.get();

    @Override
    public List<Program> getEventsForGroup(Group g) {
        return getEventsForGroupToken(g.getToken());
    }

    @Override
    public List<Program> getEventsForGroupToken(String token) {
        List<ProgramEntity> dbList = em.createNamedQuery(ProgramEntity.retrieveByGroupToken,
                ProgramEntity.class).setParameter("token", token).getResultList();

        List<Program> list = new ArrayList<Program>(dbList.size());
        
        for(ProgramEntity p : dbList) {
            list.add(p.convert());
        }
        
        return list;
    }
}
