package hu.sch.kfc.server.misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateInterval {

    private static final DateFormat df = new SimpleDateFormat("yyyy. MM. dd.");
    private static final DateFormat dtf = new SimpleDateFormat("yyyy. MM. dd. HH:mm");
    private static final DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

    private Date start;
    private Date end;

    public DateInterval() {
    }

    public DateInterval(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public boolean isOneDayLong() {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(start);
        endCal.setTime(end);
        return startCal.get(Calendar.DAY_OF_YEAR) == endCal.get(Calendar.DAY_OF_YEAR);
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getInterval() {
        StringBuilder sb = new StringBuilder();
        if (isOneDayLong()) {
            sb.append(df.format(start)).append(" ");
            sb.append(tf.format(start)).append("-").append(tf.format(end));
        } else {
            sb.append(dtf.format(start)).append(" - ");
            sb.append(dtf.format(end));
        }

        return sb.toString();
    }

    public boolean getIsEnded() {
        return end.before(new Date());
    }
}
