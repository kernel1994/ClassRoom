package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.CoursewareDAO;
import team.dx.classroom.domain.Courseware;

public class CoursewareDAOImpl extends BasicDAO<Courseware> implements CoursewareDAO {

	@Override
	public Courseware getCourseware(String condition, Object... args) {

		return get(condition, args);
	}

	@Override
	public List<Courseware> getCoursewares(String condition, Object... args) {

		return getForList(condition, args);
	}

	@Override
	public void updateCourseware(Courseware courseware) {

		String sql = "UPDATE courseware SET name = ?, description = ?, resource_id = ? WHERE id = ?";

		update(sql, courseware.getName(), courseware.getDescription(), courseware.getResource().getId(), courseware.getId());
	}

	@Override
	public void deleteCourseware(String id) {

		String sql = "DELETE FROM courseware WHERE id = ?";

		update(sql, id);
	}

	@Override
	public void addCourseware(Courseware courseware, String courseId) {

		String sql = "INSERT INTO courseware (id, name, description, resource_id, course_id) VALUES (?, ? ,?, ?, ?)";

		update(sql, courseware.getId(), courseware.getName(), courseware.getDescription(), courseware.getResource().getId(), courseId);
	}

	@Override
	public Courseware getCourseware(String condition, Object... args) {
		return get(condition, args);
	}

}
