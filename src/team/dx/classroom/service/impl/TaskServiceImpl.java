package team.dx.classroom.service.impl;

import java.util.List;

import team.dx.classroom.dao.HomeWorkDAO;
import team.dx.classroom.dao.ResourceDAO;
import team.dx.classroom.dao.TaskDAO;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.Task;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.TaskService;

public class TaskServiceImpl implements TaskService {

	private TaskDAO tDAO = ObjectFactory.getInstance().createObject(
			TaskDAO.class);
	private ResourceDAO rDAO = ObjectFactory.getInstance().createObject(
			ResourceDAO.class);
	private HomeWorkDAO hDao = ObjectFactory.getInstance().createObject(
			HomeWorkDAO.class);

	@Override
	public List<Task> getCourseTasks(String courseId) {

		String sqlTask = "select * from task where course_id = ?";
		List<Task> tasks = tDAO.getTasks(sqlTask, courseId);

		String sqlR = "select * from resource where id = ?";
		for (Task task : tasks) {
			Resource resource = rDAO.getResource(sqlR, task.getResource_id());
			task.setResource(resource);
		}

		return tasks;
	}

	@Override
	public void addTask(Task task, String courseId) {
		// 现有资源
		rDAO.addResource(task.getResource());
		tDAO.addTask(task, courseId);
	}

	@Override
	public void addHomeWork(HomeWork homeWork, String path, String standardPath) {
		hDao.add(homeWork, path, standardPath);
	}

	@Override
	public Integer getStudentTaskScore(String studentId, String taskId) {
		String sqlScore = "select score from user_task where user_id = ? and task_id = ?";

		return tDAO.getScore(sqlScore, studentId, taskId);
	}

	@Override
	public String getTaskPath(String taskId) {
		Task task = tDAO.getTask("select * from task where id = ?", taskId);
		Resource resource = rDAO.getResource("select * from resource where id = ?", task.getResource_id());
		return resource.getUri();
	}

	@Override
	public void deleteTask(String taskId) {
		Task task = tDAO.getTask("select * from task where id = ?", taskId);
		
		//删除task中记录
		tDAO.deleteTask(taskId);
		
		//删除资源resource中的记录
		
		rDAO.deleteResource(task.getResource_id());
	}
}
