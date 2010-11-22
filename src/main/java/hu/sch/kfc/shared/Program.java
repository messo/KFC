package hu.sch.kfc.shared;

import java.io.Serializable;

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
     * Rendelési időszak
     */
    private DateInterval orderInterval;
    /**
     * A rendezvény ideje
     */
    private DateInterval programInterval;
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

    public Program(DateInterval orderInterval, DateInterval programInterval, String name,
            String text) {
        this.orderInterval = orderInterval;
        this.programInterval = programInterval;
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return text;
    }

    public DateInterval getOrderInterval() {
        return orderInterval;
    }
}
