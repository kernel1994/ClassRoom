package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Task;

public interface TaskDAO {

	public List<Task> getTasks(String condition, Object ... args);

	public Task getTask(String condition, Object ... args);

	public void updateTask(Task task);
	
	public void deleteTask(String id);
	
	public void addTask(Task task, String courseId);

	/** 查询一个作业学生的分数 */
	Integer getScore(String sql, Object... args);
}
