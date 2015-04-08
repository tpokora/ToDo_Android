package mayon.org.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import mayon.org.todo.storage.Database;
import mayon.org.todo.topic.Task;

public class TaskList extends ActionBarActivity {

    private ArrayList<Task> taskList;
    private Button mainMenuButton;
    private Button addTaskButton;
    private TaskListViewAdapter adapter;
    private ListView listView;
    private Button deleteAllTaskButton;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        addTaskButton = (Button) findViewById((R.id.addTaskButton));
        addTaskButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddTaskActivity();
            }
        });

        mainMenuButton = (Button) findViewById(R.id.mainMenuButton);

        mainMenuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenuActivity();
            }
        });
        addTaskButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddTaskActivity();
            }
        });

        db = new Database(this);

        listView = (ListView) findViewById(R.id.taskListView);
        setTaskList(this);

        deleteAllTaskButton = (Button) findViewById(R.id.deleteAllTaskButton);
        deleteAllTaskButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                db.clearAll();
                setTaskList(getParent());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTaskList(Activity activity) {
        taskList = populateList();
        taskList = sortList(taskList);
        adapter = new TaskListViewAdapter(activity, taskList);
        listView.setAdapter(adapter);
    }

    public ArrayList<Task> populateList() {
        db = new Database(this);
        taskList = new ArrayList<Task>();
        for (Task task : db.getAllTasks()) {
            taskList.add(task);
            Log.d("task: ", task.getDate().toString());
        }
        return taskList;
    }

    public ArrayList<Task> sortList(ArrayList<Task> unsortedList) {
        ArrayList<Task> sortedList = new ArrayList<Task>();
        for (int i = 0; i < unsortedList.size() - 1; i++) {
            if (unsortedList.get(i).getDate().compareTo(unsortedList.get(i + 1).getDate()) == 1) {
                Task task1 = unsortedList.get(i);
                Task task2 = unsortedList.get(i + 1);

                unsortedList.set(i, task2);
                unsortedList.set(i + 1, task1);
            }
        }
        return unsortedList;
    }

    public void goToMainMenuActivity() {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }

    public void goToAddTaskActivity() {
        Intent i = new Intent(this, AddTask.class);
        startActivity(i);
    }
}
