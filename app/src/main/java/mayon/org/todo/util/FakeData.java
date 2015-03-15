package mayon.org.todo.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mayon.org.todo.storage.Database;
import mayon.org.todo.topic.Task;

/**
 * Created by Tomek on 2015-02-22.
 */
public class FakeData {
    public static ArrayList<Task> createFakeTaskList() {
        Task task1 = new Task(1, "Fake topic #1", "comment 1", null);
        Task task2 = new Task(2, "Fake topic #2", "comment 2", null);
        Task task3 = new Task(3, "Fake topic #3", "comment 3", null);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 2);
        task1.setDate((java.sql.Date) c.getTime());
        c.add(Calendar.DATE, 3);
        task2.setDate(c.getTime());
        c.add(Calendar.DATE, 4);
        task3.setDate(c.getTime());

        ArrayList<Task> fakeTaskList = new ArrayList<Task>();
        fakeTaskList.add(task1);
        fakeTaskList.add(task2);
        fakeTaskList.add(task3);

        return fakeTaskList;
    }

    public static Task fakeClosestTopic() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        Task fakeClosestTask = new Task(2, "Fake Topic", "Fake comment", c.getTime());
        return fakeClosestTask;
    }

    public static void addFakeDataToDatabase(Database db) {
        db.clearAll();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        Task task1 = new Task(1, "Fake topic from db #1", "comment 1", c.getTime());
        c.setTime(new Date());
        c.add(Calendar.DATE, 2);
        Task task2 = new Task(2, "Fake topic from db #2", "comment 2", c.getTime());
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        Task task3 = new Task(3, "Fake topic from db #3", "comment 3", c.getTime());

        db.addTask(task1);
        db.addTask(task2);
        db.addTask(task3);
    }
}
