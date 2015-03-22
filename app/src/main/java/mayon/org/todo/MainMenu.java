package mayon.org.todo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import mayon.org.todo.storage.Database;
import mayon.org.todo.topic.Task;
import mayon.org.todo.util.DateUtility;

public class MainMenu extends ActionBarActivity {

    private TextView currentDateTextView;
    private Button taskListButton;
    private Button addTaskButton;
    private Button exitButton;
    private TextView closestTaskTopicTextView;
    private TextView closestTaskDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        currentDateTextView = (TextView) findViewById(R.id.currentDate);
        currentDateTextView.setText(DateUtility.getCurrentDateString());

        closestTaskTopicTextView = (TextView) findViewById(R.id.closestTaskTopic);
        closestTaskDateTextView = (TextView) findViewById(R.id.closestTaskDate);
        setClosestTask();

        taskListButton = (Button) findViewById(R.id.taskListButton);
        taskListButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTaskListActivity();
            }
        });

        addTaskButton = (Button) findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddTaskActivity();
            }
        });

        exitButton = (Button) findViewById(R.id.exitButton);

        // exit appiaction
        exitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }
    */

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

    private void setClosestTask() {
        Task closestTask = closestTask();
        if (closestTask != null) {
            closestTaskTopicTextView.setText(closestTask.getTopic());
            closestTaskDateTextView.setText(DateUtility.getFormatedDate(closestTask.getDate()));
        } else {
            closestTaskTopicTextView.setText("No tasks on the list");
            closestTaskDateTextView.setText("");
        }
    }

    private Task closestTask() {
        Database db = new Database(this);
        return db.getClosestTaskFromDB();
    }

    private void goToTaskListActivity() {
        Intent i = new Intent(this, TaskList.class);
        startActivity(i);
    }

    private void goToAddTaskActivity() {
        Intent i = new Intent(this, AddTask.class);
        startActivity(i);
    }
}
