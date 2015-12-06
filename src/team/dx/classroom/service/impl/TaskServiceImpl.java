package team.dx.classroom.service.impl;

import java.util.List;

import team.dx.classroom.dao.HomeWorkDAO;
import team.dx.classroom.dao.ResourceDAO;
import team.dx.classroom.dao.TaskDAO;
import team.dx.classroom.dao.ThirdPartyCommonDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.Task;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.TaskService;

public class TaskServiceImpl implements TaskService {

	private UserDAO uDAO = ObjectFactory.getInstance().createObject(
			UserDAO.class);
	private TaskDAO tDAO = ObjectFactory.getInstance().createObject(
			TaskDAO.class);
	private ResourceDAO rDAO = ObjectFactory.getInstance().createObject(
			ResourceDAO.class);
	private HomeWorkDAO hDao = ObjectFactory.getInstance().createObject(
			HomeWorkDAO.class);
	private ThirdPartyCommonDAO tpcDao = ObjectFactory.getInstance().createObject(
			ThirdPartyCommonDAO.class);

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
		Resource resource = rDAO.getResource(
				"select * from resource where id = ?", task.getResource_id());
		return resource.getUri();
	}

	@Override
	public void deleteTask(String taskId) {
		Task task = tDAO.getTask("select * from task where id = ?", taskId);

		//删除所有提交作业的记录
		tpcDao.updateUserTask("deleteAll", taskId);
		
		// 删除task中记录
		tDAO.deleteTask(taskId);

		// 删除资源resource中的记录
		rDAO.deleteResource(task.getResource_id());
	}

	@Override
	public List<User> getHaveFinishStudent(String courseId, String taskId) {

		String condition = "select user.* from user,student_course where user.id = student_course.student_id and student_course.course_id='"+courseId+"' and id in (select id from user where exists (select * from user_task where user.id = user_task.user_id and user_task.task_id = '"+taskId+"'))";
		return uDAO.getUsers(condition);
	}
	
	@Override
	public List<User> getNotHaveFinishStudent(String courseId, String taskId) {

		String condition = "select user.* from user,student_course where user.id = student_course.student_id and student_course.course_id='"+courseId+"' and id not in (select id from user where exists (select * from user_task where user.id = user_task.user_id and user_task.task_id = '"+taskId+"'))";
		return uDAO.getUsers(condition);
	}

	@Override
	public Task getTask(String taskId) {
		String condition = "select * from task where id = ?";
		return tDAO.getTask(condition, taskId);
	}

	@Override
	public List<User> getNeedMarkGradeStudent(String taskId) {
		String condition = "select * from user,user_task where user.id = user_task.user_id and user_task.score2=0 and user_task.task_id='"+taskId+"';";
		return uDAO.getUsers(condition);
	}
	
	@Override
	public List<User> getNoNeedMarkGradeStudent(String taskId) {
		String condition = "select * from user,user_task where user.id = user_task.user_id and user_task.score2!=0 and user_task.task_id='"+taskId+"';";
		return uDAO.getUsers(condition);
	}

	@Override
	public void markScore2(String taskId, String studentId, int score2) {
		tpcDao.updateUserTask("update", score2,studentId,taskId);
	}
}
