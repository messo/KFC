package hu.sch.kfc.domain;

import hu.sch.kfc.misc.AbstractEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Egy kajás kört reprezentál, akinek rendezvényei vannak, illetve különböző kaja típusokat gyárt,
 * amelyeket a rendezvényeken lehet megrendelni.
 * 
 * @author messo
 * @stereotype entity
 * @composed - has * Program
 * @has 1 produces * Food
 */
@Entity
@Table(name = "groups")
@NamedQueries({
        @NamedQuery(name = Group.retrieveAll, query = "SELECT g FROM Group g"),
        @NamedQuery(name = Group.retrieveByToken, query = "SELECT g FROM Group g "
                + "WHERE g.token = :token") })
public class Group implements AbstractEntity {

    public static final String retrieveAll = "retrieveAll";
    public static final String retrieveByToken = "retrieveByToken";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "organizer")
    private List<Program> programs;

    @OneToMany(mappedBy = "producer")
    private List<Food> foods;

    @Version
    @Column(name = "version")
    private Integer version;

    @Override
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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
