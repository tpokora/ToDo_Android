package mayon.org.todo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import mayon.org.todo.topic.Task;
import mayon.org.todo.util.DateFormater;

/**
 * Created by Tomek on 2015-02-22.
 */
public class TaskListViewAdapter extends BaseAdapter {

    private ArrayList<Task> list;
    private Activity activity;

    public TaskListViewAdapter(Activity activity, ArrayList<Task> list) {
        super();
        this.list = list;
        this.activity = activity;
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
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Task task = list.get(position);
        viewHolder.topic.setText(task.getTopic());
        viewHolder.date.setText(DateFormater.getFormatedDate(task.getDate()));
        return convertView;
    }
}
