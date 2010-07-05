package hu.sch.kfc.server.domain;

import hu.sch.kfc.shared.Group;
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

@Entity
@Table(name = "groups")
@NamedQueries( {
        @NamedQuery(name = GroupEntity.retrieveAll, query = "SELECT g FROM GroupEntity g"),
        @NamedQuery(name = GroupEntity.retrieveByToken, query = "SELECT g FROM GroupEntity g "
                + "WHERE g.token = :token") })
public class GroupEntity implements IsEntity<Group> {

    public static final String retrieveAll = "retrieveAll";
    public static final String retrieveByToken = "retrieveByToken";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String token;
    private String name;
    private List<ProgramEntity> programs;

    public GroupEntity() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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

    @OneToMany
    public List<ProgramEntity> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramEntity> programs) {
        this.programs = programs;
    }

    @Override
    public Group convert() {
        return new Group(name, token);
    }
}
