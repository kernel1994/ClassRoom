package team.dx.classroom.dao.impl;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.CourseDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;

import java.util.List;

public class CourseDAOImpl extends BasicDAO<Course> implements CourseDAO {

	@Override
	public List<Course> getCourses(String condition, Object... args) {

		try {
			return getForList(condition, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Course> getStudentCourses(String studentId) {
		String sqlC = "select * from course as c "
				+ "where c.id "
				+ "in ("
				+ "select course_id "
				+ "from student_course as sc "
				+ "where sc.student_id = ?"
				+ ")";

		List<Course> courses = getCourses(sqlC, studentId);

		/* f**k */
		String sqlT = "select * from user "
				+ "where id = ("
				+ "select teacher_id "
				+ "from course as c "
				+ "where c.id = ?"
				+ ")";

		UserDAO uDAO = ObjectFactory.getInstance().createObject(UserDAO.class);
		for (Course c : courses) {
			User teacher = uDAO.getUser(sqlT, c.getId());
			c.setTeacher(teacher);
			c.setHaveOwn(1);
		}

		return courses;
	}

	@Override
	public Course getCourse(String condition, Object... args) {

		return get(condition, args);
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
	public void addCourse(Course course) throws RuntimeException{

		String sql = "INSERT INTO course (id, name, limitperson, description, teacher_id) VALUES (?, ?, ?, ?, ?)";

		update(sql, course.getId(), course.getName(), course.getLimitperson(), course.getDescription(), course.getTeacher().getId());
	}

}
