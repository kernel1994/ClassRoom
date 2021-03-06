package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.TaskDAO;
import team.dx.classroom.domain.Task;

public class TaskDAOImpl extends BasicDAO<Task> implements TaskDAO {

	@Override
	public List<Task> getTasks(String condition, Object... args) {
		
		return getForList(condition, args);
	}

	@Override
	public Task getTask(String condition, Object... args) {

		return get(condition, args);
	}

	@Override
	public void updateTask(Task task) {

		String sql = "UPDATE task SET name = ?, description = ? resource_id = ? WHERE id = ?";
		
		update(sql, task.getName(), task.getDescription(), task.getResource().getId(), task.getId());
	}

	@Override
	public void deleteTask(String id) {

		String sql = "DELETE FROM task WHERE id = ?";
		
		update(sql, id);
	}

	@Override
	public void addTask(Task task, String courseId) {

		String sql = "INSERT INTO task (id, name, description,course_id, resource_id) VALUES (?, ?, ?, ?, ?)";
		
		update(sql, task.getId(), task.getName(), task.getDescription(), courseId, task.getResource().getId());
	}

	@Override
	public Integer getScore(String sql, Object... args) {

		return getTheValue(sql, args);
	}
}
