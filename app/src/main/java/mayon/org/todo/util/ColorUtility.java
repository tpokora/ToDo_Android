package mayon.org.todo.util;

import android.graphics.Color;

import java.util.Date;

/**
 * Created by Tomek on 2015-04-05.
 */
public class ColorUtility {
    public int setColorAccordingToDate(Date topicDate) {
        int daysLeft = daysLeft(topicDate);
        if (daysLeft < 6 && daysLeft > 3) {
            return Color.GREEN;
        } else if (daysLeft < 4 && daysLeft > 1) {
            return Color.YELLOW;
        } else if (daysLeft < 2) {
            return Color.RED;
        } else {
            return Color.WHITE;
        }
    }

    private static int daysLeft(Date topicDate) {
        Date currentDate = DateUtility.getCurrentDate();
        int days = (int) ((topicDate.getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24));
        return days;
    }

}
