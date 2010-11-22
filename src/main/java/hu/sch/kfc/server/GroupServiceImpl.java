package hu.sch.kfc.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import hu.sch.kfc.client.service.GroupService;
import hu.sch.kfc.server.domain.GroupEntity;
import hu.sch.kfc.server.domain.ProgramEntity;
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

        if(dbList.isEmpty()) {
            try {
                em.getTransaction().begin();
                // alap adatok feltöltése
                GroupEntity ge = new GroupEntity();
                ge.setName("Pizzásch");
                ge.setToken("pizzasch");
                em.persist(ge);
                GroupEntity ge2 = new GroupEntity();
                ge2.setName("Gyrososch");
                ge2.setToken("gyrososch");
                em.persist(ge2);
                ProgramEntity pe = new ProgramEntity();
                pe.setOrganizer(ge);
                pe.setName("Szuper móka");
                pe.setStart(new Date(2010, 12, 06, 5, 0, 0));
                pe.setEnd(new Date(2010, 12, 06, 8, 0, 0));
                pe.setOrderStart(new Date(2010, 12, 06, 2, 0, 0));
                pe.setOrderEnd(new Date(2010, 12, 06, 4, 0, 0));
                em.persist(pe);

                dbList.add(ge);
                dbList.add(ge2);
                em.flush();
            } catch(Exception ex) {
            }
        }

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
