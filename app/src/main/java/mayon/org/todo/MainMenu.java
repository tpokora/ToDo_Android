package mayon.org.todo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import java.util.Calendar;
import java.util.Date;

import mayon.org.todo.topic.Task;
import mayon.org.todo.util.DateFormater;
import mayon.org.todo.util.FakeData;

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
        currentDateTextView.setText(getCurrentDate());

        closestTaskTopicTextView = (TextView) findViewById(R.id.closestTaskTopic);
        closestTaskDateTextView = (TextView) findViewById(R.id.closestTaskDate);

        /*
            Fake closest topic
         */
        Task fakeTask = FakeData.fakeClosestTopic();
        closestTaskTopicTextView.setText(fakeTask.getTopic());
        closestTaskDateTextView.setText(DateFormater.getFormatedDate(fakeTask.getDate()));

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

    private String getCurrentDate() {
        return DateFormater.getFormatedDate(new Date());
    }

    public void goToTaskListActivity() {
        Intent i = new Intent(this, TaskList.class);
        startActivity(i);
    }

    public void goToAddTaskActivity() {
        Intent i = new Intent(this, AddTask.class);
        startActivity(i);
    }
}
