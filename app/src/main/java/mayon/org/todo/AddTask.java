package mayon.org.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


import mayon.org.todo.error.Error;
import mayon.org.todo.storage.Database;
import mayon.org.todo.topic.Task;
import mayon.org.todo.util.DateUtility;


public class AddTask extends ActionBarActivity {

    private EditText addTaskTopicEditText;
    private EditText addTaskDateEditText;
    private Button addDateButton;
    private DatePicker datePicker;
    private TextView errorAddTaskTopicTextView;
    private TextView errorAddTaskDateTextView;

    private int year, month, day;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        addTaskTopicEditText = (EditText) findViewById(R.id.addTaskFormEditText);
        addTaskDateEditText = (EditText) findViewById(R.id.addTaskDate);
        addTaskDateEditText.setText(DateUtility.getFormatedDate(new Date()));
        addDateButton = (Button) findViewById(R.id.dataPickButton);
        datePicker = new DatePicker(this);
        errorAddTaskTopicTextView = (TextView) findViewById(R.id.errorAddTaskTopicLabel);
        errorAddTaskDateTextView = (TextView) findViewById(R.id.errorAddTaskDateLabel);

        setDateOnDatePicker();
        addDatePickerEditTextListener();
        addDateButtonListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
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

    private void setDateOnDatePicker() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, null);
    };

    public void addDatePickerEditTextListener() {
        addTaskDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    public void addDateButtonListener() {
        addDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAddTaskTopicEmpty()) {
                    errorAddTaskTopicTextView.setVisibility(View.VISIBLE);
                    errorAddTaskTopicTextView.setText(Error.ERROR_EMPTY_FILED);
                } else {
                    addTask();
                }
            }
        });
    }

    public void addTask() {
        Database db = new Database(this);
        Task newTask = new Task(addTaskTopicEditText.getText().toString(),
                DateUtility.stringToDate(addTaskDateEditText.getText().toString()));
        db.addTask(newTask);
        goToTaskListActivity();
    }

    private void goToTaskListActivity() {
        Intent i = new Intent(this, TaskList.class);
        startActivity(i);
    }

    /*
    private boolean checkFieldsCorrection() {
        boolean correct = false;
        if (isAddTaskTopicEmpty() && !isAddTaskDateCorrect()) {
            errorAddTaskDateTextView.setVisibility(View.VISIBLE);
            errorAddTaskDateTextView.setText(Error.ERROR_WRONG_DATE);
        } else {
            correct = true;
            errorAddTaskTopicTextView.setVisibility(View.INVISIBLE);
        }
    }
    */

    private boolean isAddTaskTopicEmpty() {
        if (addTaskTopicEditText.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAddTaskDateCorrect() {
        String addDateString = addTaskDateEditText.getText().toString();
        Date addDate = DateUtility.stringToDate(addDateString);
        Date currentDate = DateUtility.getCurrentDate();
        if (addDate.before(currentDate)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int selectedYear,
                                      int selectedMonth, int selectedDay) {
                    year = selectedYear;
                    month = selectedMonth + 1;
                    day = selectedDay;

                    addTaskDateEditText.setText(new StringBuilder().append(day)
                        .append("-").append(month).append("-").append(year).append(""));
                }
            };


}
