package hu.sch.kfc.server.domain;

import hu.sch.kfc.server.EMF;
import hu.sch.kfc.server.misc.DateInterval;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Egy adott rendezvényhez tartozó rendelési intervallum, ahol meg lehet szabni, hogy mennyit lehet
 * rendelni, és jegyezzük, hogy eddig mennyit rendeltek.
 * 
 * @author messo
 * @stereotype entity
 */
@Entity
@Table(name = "order_intervals")
public class OrderInterval implements Comparable<OrderInterval> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "program")
    private Program program;

    @Column(name = "orderStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "orderEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @Column(name = "orderLimit")
    private Integer limit;

    @Column(name = "orderCount", nullable = false)
    private Integer count;
    
    @Version
    @Column(name = "version")
    private Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date orderStart) {
        this.start = orderStart;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date orderEnd) {
        this.end = orderEnd;
    }
    
    public Integer getVersion() {
        return version;
    }

    public DateInterval getInterval() {
        return new DateInterval(start, end);
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int compareTo(OrderInterval o) {
        int c = start.compareTo(o.start);
        if (c == 0)
            c = end.compareTo(o.end);

        return c;
    }
    
    public void persist() {
        System.out.println("ORDER INTERVAL PERSIST!");
        System.out.println(this);
    }
    
    public static OrderInterval findOrderInterval(Long id) {
        EntityManager em = EMF.get();
        try {
            em.getTransaction().begin();
            return em.find(OrderInterval.class, id);
        } finally {
            em.close();
        }
    }
}
