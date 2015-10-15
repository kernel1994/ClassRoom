package team.dx.classroom.service.impl;

import java.util.List;

import team.dx.classroom.dao.CourseDAO;
import team.dx.classroom.domain.Course;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.TeacherBusinessService;

public class TeacherBusinessServiceImpl implements TeacherBusinessService {
	
	private CourseDAO cDao = ObjectFactory.getInstance().createObject(CourseDAO.class);

	@Override
	public List<Course> getAllCourses(String teacherId) throws RuntimeException {
			String condition = "select * from course where teacher_id = ?";
			return cDao.getCourses(condition, teacherId);
	}

	@Override
	public void addCourses(Course course) throws RuntimeException {
		cDao.addCourse(course);
	}

}
