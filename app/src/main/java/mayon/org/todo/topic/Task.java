package mayon.org.todo.topic;

import java.util.Date;

/**
 * Created by Tomek on 2015-02-19.
 */
public class Task {
    private long id;
    private String topic;
    private String comment;
    private Date date;

    public Task() {}

    public Task(int id, String topic, String comment, Date date) {
        this.id = id;
        this.topic = topic;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
