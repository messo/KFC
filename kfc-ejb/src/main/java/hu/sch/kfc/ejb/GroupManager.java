package hu.sch.kfc.ejb;

import hu.sch.kfc.domain.Group;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class GroupManager {

    @PersistenceContext
    private EntityManager em;

    public Group findGroup(Long id) {
        return em.find(Group.class, id);
    }

    public Group findGroupByToken(String token) {
        return em.createNamedQuery(Group.retrieveByToken, Group.class).setParameter("token", token)
                .getSingleResult();
    }

    public List<Group> findGroups() {
        return em.createNamedQuery(Group.retrieveAll, Group.class).getResultList();
    }
}
