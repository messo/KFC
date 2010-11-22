package hu.sch.kfc.shared;

import hu.sch.kfc.client.util.DateHolder;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DateInterval implements Serializable {

    private Date start;
    private Date end;

    public DateInterval() {
    }

    public DateInterval(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public boolean isOneDayLong() {
        return start.getYear() == end.getYear() && start.getMonth() == end.getMonth()
                && start.getDate() == end.getDate();
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public boolean isEnded() {
        Date now = DateHolder.getCurrentDate();
        return now.after(end);
    }
}
