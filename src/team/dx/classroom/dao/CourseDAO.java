package team.dx.classroom.dao;

import team.dx.classroom.domain.Course;

import java.util.List;

public interface CourseDAO {

	public List<Course> getCourses(String condition, Object ... args);

	List<Course> getStudentCourses(String studentId);

	public  Course getCourse(String condition, Object... args);

	public void updateCourse(Course course);
	
	public void deleteCourse(String id);
	
	public  void addCourse(Course course);
}
