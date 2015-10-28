package team.dx.classroom.domain;

import java.util.List;

public class Course {

    private String id;
    private String name;
    private int limitperson;
    private String description;
    private User teacher;
    private List<Courseware> coursewares;
    private List<Task> tasks;

    /**
     * 用于标注用户是否用于该课程
     * 1 表示拥有
     * 0 表示没有
     */
    private int haveOwn = 0;

    public Course() {
    }

    public Course(String id, String name, int limitperson, String description, User teacher, List<Courseware> coursewares, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.limitperson = limitperson;
        this.description = description;
        this.teacher = teacher;
        this.coursewares = coursewares;
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimitperson() {
        return limitperson;
    }

    public void setLimitperson(int limitperson) {
        this.limitperson = limitperson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<Courseware> getCoursewares() {
        return coursewares;
    }

    public void setCoursewares(List<Courseware> coursewares) {
        this.coursewares = coursewares;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", limitperson=" + limitperson +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                ", coursewares=" + coursewares +
                ", tasks=" + tasks +
                ", haveOwn=" + haveOwn +
                '}';
    }

    public int getHaveOwn() {
        return haveOwn;
    }

    public void setHaveOwn(int haveOwn) {
        this.haveOwn = haveOwn;
    }

}
