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
    
    /***
     * 添加作业到数据库
     *  @param courseId 指定的课程id
     *  @task 作业的信息
     * 
     * */
    void addTask(Task task, String courseId);
    
    
    /***
     *  添加作业到硬盘(用xml文件保存)
     *  @param homeWork 作业信息
     *  @path 作业的的保存路径
     *  @standardPath 空作业的路径
     * */
    void addHomeWork(HomeWork homeWork, String path, String standardPath);
    
    /**
     * 获取学生某次作业的分数
     * */
    Integer getStudentTaskScore(String studentId, String taskId);

}
