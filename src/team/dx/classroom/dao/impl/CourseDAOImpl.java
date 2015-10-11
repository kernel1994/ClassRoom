package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.CourseDAO;
import team.dx.classroom.domain.Course;

public class CourseDAOImpl extends BasicDAO<Course> implements CourseDAO {

	@Override
	public List<Course> getCourses(String condition, Object... args) {

		return getForList(condition, args);
	}

	@Override
	public void updateCourse(Course course) {

		String sql = "UPDATE course SET name = ?, limitperson = ?, description = ?, teacher_id = ? WHERE id = ?";

		update(sql, course.getName(), course.getLimitperson(), course.getDescription(), course.getTeacher().getId(), course.getId());
	}

	@Override
	public void deleteCourse(String id) {

		String sql = "DELETE FROM course WHERE id = ?";

		update(sql, id);
	}

	@Override
	public void addCourse(Course course) {

		String sql = "INSERT INTO course (id, name, limitperson, description, teacher_id) VALUES (?, ?, ?, ?, ?)";

		update(sql, course.getId(), course.getName(), course.getLimitperson(), course.getDescription(), course.getTeacher().getId());
	}

}
