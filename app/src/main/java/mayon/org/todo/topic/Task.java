package mayon.org.todo.topic;

import java.util.Date;

/**
 * Created by Tomek on 2015-02-19.
 */
public class Task {
    private String topic;
    private String comment;
    private Date date;

    public Task() {}

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
