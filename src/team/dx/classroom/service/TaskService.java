package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Task;
import team.dx.classroom.domain.User;

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

    /**
     * 获取一次作业的路径
     * @taskId 作业的保存路径id
     * @pathName 作业的的保存路径
     * */
	String getTaskPath(String taskId);

	/**
     * 删除一次作业
     * @taskId 作业的id
     * */
	void deleteTask(String taskId);
	
	/**
     * 找出已经做了作业的所有学生
     * @taskId 作业的id
     * @courseId 课程的id
     * @return 完成作业List 学生对象
     * */
	List<User> getHaveFinishStudent(String courseId, String taskId);

	/**
     * 找出还没有做了作业的所有学生
     * @taskId 作业的id
     * @courseId 课程的id
     * @return 还没有完成作业List 学生对象
     * */
	List<User> getNotHaveFinishStudent(String courseId, String taskId);

	/**
     * 作业的描述
     * @taskId 作业的id
     * @return 作业对象
     * */
	Task getTask(String taskId);

	/**
     * 找出还需要批改作业的所有学生
     * @taskId 作业的id
     * @return 还没有完成作业List 学生对象
     * */
	List<User> getNeedMarkGradeStudent(String taskId);
	
	/**
     * 找出不需要批改作业的所有学生
     * @taskId 作业的id
     * @return 还没有完成作业List 学生对象
     * */
	List<User> getNoNeedMarkGradeStudent(String taskId);

	public void markScore2(String taskId, String studentId, int score2);
}
