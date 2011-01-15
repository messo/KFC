package hu.sch.kfc.server.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Egy rendelést ír le: ki rendelte, mit rendelt és melyik intervallumra.
 * 
 * @author messo
 * @stereotype entity
 * @navassoc * what 1 Food
 * @navassoc * who 1 User
 * @navassoc * when 1 OrderInterval
 */
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food")
    private Food food;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "interval")
    private OrderInterval interval;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderInterval getInterval() {
        return interval;
    }

    public void setInterval(OrderInterval interval) {
        this.interval = interval;
    }
}
