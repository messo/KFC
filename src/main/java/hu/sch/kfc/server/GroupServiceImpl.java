package hu.sch.kfc.server;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import hu.sch.kfc.client.service.GroupService;
import hu.sch.kfc.server.domain.GroupEntity;
import hu.sch.kfc.shared.Group;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GroupServiceImpl extends RemoteServiceServlet implements GroupService {

    private static final EntityManager em = EMF.get();

    /**
     * Lekérjük az összes csoportot
     * 
     * @return csoportok listája
     */
    @Override
    public List<Group> getGroups() {
        List<GroupEntity> dbList = em.createNamedQuery(GroupEntity.retrieveAll, GroupEntity.class)
                .getResultList();

        List<Group> list = new ArrayList<Group>(dbList.size());
        for (GroupEntity g : dbList) {
            list.add(g.convert());
        }
        return list;
    }

    /**
     * Egy csoportot kérünk le a token alapján
     * 
     * @param csoport tokenje
     * @return a csoport, akinek ez a tokenje
     */
    @Override
    public Group getGroupByToken(String token) {
        try {
            GroupEntity g = em.createNamedQuery(GroupEntity.retrieveByToken, GroupEntity.class)
                    .setParameter("token", token).getSingleResult();
            
            return g.convert();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
