package hu.sch.kfc.server.domain;

import hu.sch.kfc.shared.Program;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "programs")
@NamedQueries( { @NamedQuery(name = ProgramEntity.retrieveByGroupToken, query = "SELECT p FROM ProgramEntity p "
        + "LEFT JOIN FETCH p.organizer WHERE p.organizer.token = :token") })
public class ProgramEntity implements IsEntity<Program> {

    public static final String retrieveByGroupToken = "retrieveByGroupToken";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private GroupEntity organizer;

    public ProgramEntity() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn
    public GroupEntity getOrganizer() {
        return organizer;
    }

    public void setOrganizer(GroupEntity organizer) {
        this.organizer = organizer;
    }

    @Override
    public Program convert() {
        return new Program(null, null, name, "bla-bla-bla-bla");
    }
}
