package mayon.org.todo;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mayon.org.todo.storage.Database;
import mayon.org.todo.topic.Task;
import mayon.org.todo.util.ColorUtility;
import mayon.org.todo.util.DateUtility;

/**
 * Created by Tomek on 2015-02-22.
 */
public class TaskListViewAdapter extends BaseAdapter {

    private ArrayList<Task> list;
    private TaskList activity;
    private ColorUtility colorUtil;
    private Database database;

    public TaskListViewAdapter(Activity activity, ArrayList<Task> list) {
        super();
        this.list = list;
        this.activity = (TaskList) activity;
        colorUtil = new ColorUtility();
        database = new Database(activity);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView topic;
        TextView date;
        Button deleteTaskButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.task_list_row, null);
            viewHolder = new ViewHolder();
            viewHolder.topic = (TextView) convertView.findViewById(R.id.taskListRowTopic);
            viewHolder.date = (TextView) convertView.findViewById(R.id.taskListRowDate);
            viewHolder.deleteTaskButton = (Button) convertView.findViewById(R.id.deleteTaskButton);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Task task = list.get(position);
        viewHolder.topic.setText(task.getTopic());
        viewHolder.date.setText(DateUtility.getFormatedDate(task.getDate()));
        viewHolder.date.setTextColor(colorUtil.setColorAccordingToDate(task.getDate()));
        viewHolder.deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTaskListener(task.getId());
            }
        });
        return convertView;
    }

    public void deleteTaskListener(long taskId) {
        database.deleteTask(taskId);
        activity.setTaskList(activity);
    }
}
