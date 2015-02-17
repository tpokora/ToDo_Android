package mayon.org.todo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainMenu extends ActionBarActivity {

    private TextView currentDateTextView;
    private Button taskListButton;
    private Button addTaskButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        currentDateTextView = (TextView) findViewById(R.id.currentDate);
        currentDateTextView.setText(getCurrentDate());

        taskListButton = (Button) findViewById(R.id.taskListButton);

        addTaskButton = (Button) findViewById(R.id.addTaskButton);

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

    public static String getCurrentDate() {
        Date currentDate = new Date();
        String currentDateString = new SimpleDateFormat("dd-MM-yyyy").format(currentDate);
        return currentDateString;
    }
}
