package mayon.org.todo.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import mayon.org.todo.topic.Task;
import mayon.org.todo.util.DateUtility;

/**
 * Created by Tomek on 2015-02-22.
 */
public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "tasks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table tasks(" +
                        "id integer primary key autoincrement, " +
                        "topic text, " +
                        "date text" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> allTasks = new ArrayList<Task>();
        String columns[] = {"id", "topic", "date"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("tasks", columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getLong(0));
            task.setTopic(cursor.getString(1));
            task.setDate(DateUtility.stringToDate(cursor.getString(2)));
            allTasks.add(task);
        }
        return allTasks;
    }

    public void addTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("topic", task.getTopic());
        values.put("date", DateUtility.getFormatedDate(task.getDate()));
        Log.d("addTask date",  DateUtility.getFormatedDate(task.getDate()));
        db.insertOrThrow("tasks", null, values);
    }

    public void deleteTask(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {"" + id};
        db.delete("tasks", "id=?", args);
    }

    public void clearAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from tasks");
    }

    public Task getClosestTaskFromDB() {
        Task closestTask = null;
        ArrayList<Task> allTasks = getAllTasks();
        if (allTasks != null) {
            for (Task task : allTasks) {
                if (closestTask == null) {
                    closestTask = task;
                }
                if (task.getDate().before(closestTask.getDate())) {
                    closestTask = task;
                }
            }
        }
        return closestTask;
    }
}
