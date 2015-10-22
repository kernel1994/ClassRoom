package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Task;

public interface TaskService {

    /**
     * 获得指定课程号的所有课件列表
     * @param courseId 指定的课程id
     * @return 该课程号的所有课件List 对象
     * */
    public List<Task> getCourseTasks(String courseId);
    
    void addTask(Task task, String courseId);
    
    void addHomeWork(HomeWork homeWork, String path, String standardPath);
}
