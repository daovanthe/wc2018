package th.wc2018.ulity;

import java.util.Calendar;
import java.util.Date;

public class DateUnity {
    public static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
