package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Course;

public interface CourseDAO {

	public List<Course> getCourses(String condition, Object ... args);
	
	public void updateCourse(Course course);
	
	public void deleteCourse(String id);
	
	public void addCourse(Course course);
}
