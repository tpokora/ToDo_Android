package mayon.org.todo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tomek on 2015-02-22.
 */
public class DateFormater {
    public static String getFormatedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }
}
