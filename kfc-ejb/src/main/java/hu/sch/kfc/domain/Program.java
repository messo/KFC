package hu.sch.kfc.domain;

import hu.sch.kfc.misc.AbstractEntity;
import hu.sch.kfc.misc.DateInterval;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Egy kör által szervezett rendezvényt ír le. Meg lehet adni a rendelhető kajákat, illetve, hogy
 * milyen rendelési intervallumok (legalább egynek lennie kell) vannak, azaz mikorra lehet rendelni
 * a kajákat.
 * 
 * @author messo
 * @stereotype entity
 * @has * provides * Food
 * @composed 1 "consists of" 1..* OrderInterval
 */
@Entity
@Table(name = "programs")
@NamedQueries({ @NamedQuery(name = Program.retrieveByGroupToken, query = "SELECT p FROM Program p "
        + "LEFT JOIN FETCH p.organizer WHERE p.organizer.token = :token") })
public class Program implements AbstractEntity {

    public static final String retrieveByGroupToken = "retrieveByGroupToken";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "organizer", nullable = false)
    private Group organizer;

    @Column(name = "programStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "programEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @ManyToMany(mappedBy = "programs")
    private List<Food> orderableFoods;

    @OneToMany(mappedBy = "program")
    private List<OrderInterval> orderIntervals;

    @Version
    @Column(name = "version")
    private Integer version;

    public Program() {
        super();
    }

    @Override
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

    public List<Food> getOrderableFoods() {
        return orderableFoods;
    }

    public void setOrderableFoods(List<Food> orderableFoods) {
        this.orderableFoods = orderableFoods;
    }

    public List<OrderInterval> getOrderIntervals() {
        return orderIntervals;
    }

    public void setOrderIntervals(List<OrderInterval> orderIntervals) {
        this.orderIntervals = orderIntervals;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Lekérjük a rendezvény összesített rendelési idejét
     * 
     * @return összesített rendelési idő
     */
    public DateInterval getOrderInterval() {
        OrderInterval[] intervals = orderIntervals
                .toArray(new OrderInterval[orderIntervals.size()]);
        Arrays.sort(intervals);
        return new DateInterval(intervals[0].getStart(), intervals[intervals.length - 1].getEnd());
    }

    /**
     * Lekérjük a rendezvényt szervező körnek a tokenjét. Ezt akkor használjuk, amikor egy adott
     * rendezvényről vissza akarunk navigálni a körhöz.
     * 
     * @return rendező körnek a tokenje
     */
    public String getGroupToken() {
        return organizer.getToken();
    }
}
