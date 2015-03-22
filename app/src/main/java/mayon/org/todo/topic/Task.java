package mayon.org.todo.topic;

import java.util.Date;

/**
 * Created by Tomek on 2015-02-19.
 */
public class Task {
    private long id;
    private String topic;
    private Date date;

    public Task() {}

    public Task(int id, String topic, Date date) {
        this.id = id;
        this.topic = topic;
        this.date = date;
    }

    public Task(String topic, Date date) {
        this.topic = topic;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
