package mayon.org.todo.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mayon.org.todo.topic.Task;

/**
 * Created by Tomek on 2015-02-22.
 */
public class FakeData {
    public static ArrayList<Task> createFakeTaskList() {
        Task task1 = new Task("Fake topic #1", "comment 1", null);
        Task task2 = new Task("Fake topic #2", "comment 2", null);
        Task task3 = new Task("Fake topic #3", "comment 3", null);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 2);
        task1.setDate(c.getTime());
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
        Task fakeClosestTask = new Task("Fake Topic", "Fake comment", c.getTime());
        return fakeClosestTask;
    }
}
