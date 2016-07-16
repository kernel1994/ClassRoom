package team.dx.classroom.domain;

import java.util.Date;

/**
 * Created by kernel on 2016/7/16.
 */
public class Announcement {

    private String id;
    private User user;
    private String user_id;
    private Date time;
    private String course_id;
    private String courseName;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", user_id='" + user_id + '\'' +
                ", time=" + time +
                ", course_id='" + course_id + '\'' +
                ", courseName='" + courseName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
