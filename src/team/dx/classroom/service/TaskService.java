package team.dx.classroom.service;

import team.dx.classroom.domain.Task;

import java.util.List;

public interface TaskService {

    /**
     * 获得指定课程号的所有课件列表
     * @param courseId 指定的课程id
     * @return 该课程号的所有课件List 对象
     * */
    public List<Task> getCourseTasks(String courseId);
}
