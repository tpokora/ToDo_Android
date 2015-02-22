package mayon.org.todo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import mayon.org.todo.topic.Task;
import mayon.org.todo.util.FakeData;


public class TaskList extends ActionBarActivity {

    private ArrayList<Task> taskList;
    private Button mainMenuButton;
    private Button addTaskButton;
    private TaskListViewAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        mainMenuButton = (Button) findViewById(R.id.mainMenuButton);
        addTaskButton = (Button) findViewById((R.id.addTaskButton));

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

        listView = (ListView) findViewById(R.id.taskListView);
        taskList = FakeData.createFakeTaskList();
        adapter = new TaskListViewAdapter(this, taskList);
        listView.setAdapter(adapter);
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

    public void goToMainMenuActivity() {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }

    public void goToAddTaskActivity() {
        Intent i = new Intent(this, AddTask.class);
        startActivity(i);
    }
}
