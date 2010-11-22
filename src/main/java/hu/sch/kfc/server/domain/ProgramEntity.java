package hu.sch.kfc.server.domain;

import java.util.Date;
import hu.sch.kfc.shared.DateInterval;
import hu.sch.kfc.shared.Program;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @Column(columnDefinition = "TEXT")
    private String description;
    private GroupEntity organizer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderEnd;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getOrderStart() {
        return orderStart;
    }

    public void setOrderStart(Date orderStart) {
        this.orderStart = orderStart;
    }

    public Date getOrderEnd() {
        return orderEnd;
    }

    public void setOrderEnd(Date orderEnd) {
        this.orderEnd = orderEnd;
    }

    @Override
    public Program convert() {
        return new Program(new DateInterval(orderStart, orderEnd), new DateInterval(start, end),
                name, description);
    }
}
