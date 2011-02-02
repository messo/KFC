package hu.sch.kfc.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Egy rendelhető kaját ír le, amit egy adott kör gyárt.
 * 
 * @author messo
 * @stereotype entity
 */
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producer")
    private Group producer;

    @ManyToMany
    @JoinTable(name = "orderable_foods", joinColumns = @JoinColumn(name = "food_id"), inverseJoinColumns = @JoinColumn(name = "program_id"))
    private List<Program> programs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getProducer() {
        return producer;
    }

    public void setProducer(Group producer) {
        this.producer = producer;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}
