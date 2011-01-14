package hu.sch.kfc.server.domain;

import hu.sch.kfc.server.EMF;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "groups")
@NamedQueries({
        @NamedQuery(name = Group.retrieveAll, query = "SELECT g FROM Group g"),
        @NamedQuery(name = Group.retrieveByToken, query = "SELECT g FROM Group g "
                + "WHERE g.token = :token") })
public class Group {

    public static final String retrieveAll = "retrieveAll";
    public static final String retrieveByToken = "retrieveByToken";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "organizer")
    private List<Program> programs;

    @Version
    @Column(name = "version")
    private Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static Group findGroup(Long id) {
        return null;
    }

    public static List<Group> findGroups() {
        EntityManager em = EMF.get();
        try {
            em.getTransaction().begin();
            return em.createNamedQuery(Group.retrieveAll, Group.class).getResultList();
        } finally {
            em.close();
        }
    }

    public static Group findGroupByToken(String token) {
        EntityManager em = EMF.get();
        try {
            return em.createNamedQuery(Group.retrieveByToken, Group.class)
                    .setParameter("token", token).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
