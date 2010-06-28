package hu.sch.kfc.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Rendezvény objektumot reprezentál, ebből egy időben több is lehet, lehet átfedés a rendelési
 * időszakok között
 *
 * @author messo
 * @since 0.1
 */
@SuppressWarnings("serial")
public class Program implements Serializable {

    /**
     * Innentől kezdve lehet rendelni
     */
    private Date start;
    /**
     * Ekkor záródik le a rendelés időszak
     */
    private Date end;
    /**
     * Az esemény neve.
     */
    private String name;
    /**
     * Az eseményről egy rövid leírás.
     */
    private String text;

    public Program() {
        // TODO Auto-generated constructor stub
    }

    public Program(Date start, Date end, String name, String text) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.text = text;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return text;
    }
}
