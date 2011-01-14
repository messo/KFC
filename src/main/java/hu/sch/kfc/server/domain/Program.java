package hu.sch.kfc.server.domain;

import hu.sch.kfc.server.EMF;
import hu.sch.kfc.server.misc.DateInterval;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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
import javax.persistence.Version;

@Entity
@Table(name = "programs")
@NamedQueries({ @NamedQuery(name = Program.retrieveByGroupToken, query = "SELECT p FROM Program p "
        + "LEFT JOIN FETCH p.organizer WHERE p.organizer.token = :token") })
public class Program {

    public static final String retrieveByGroupToken = "retrieveByGroupToken";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "organizer")
    private Group organizer;

    @Column(name = "programStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "programEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @Column(name = "orderStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderStart;

    @Column(name = "orderEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderEnd;

    @Version
    @Column(name = "version")
    private Integer version;

    public Program() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Group getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Group organizer) {
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public DateInterval getOrderInterval() {
        return new DateInterval(orderStart, orderEnd);
    }

    public static Program findProgram(Long id) {
        return null;
    }

    public static List<Program> findProgramsForGroup(Group g) {
        return findProgramsByGroupToken(g.getToken());
    }

    public static List<Program> findProgramsByGroupToken(String token) {
        EntityManager em = EMF.get();
        return em.createNamedQuery(Program.retrieveByGroupToken, Program.class)
                .setParameter("token", token).getResultList();
    }
}
