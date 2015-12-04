package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Courseware;

public interface CoursewareDAO {

	public List<Courseware> getCoursewares(String condition, Object ... args);
	
	public Courseware getCourseware(String condition, Object... args);
	
	public void updateCourseware(Courseware courseware);
	
	public void deleteCourseware(String id);
	
	public void addCourseware(Courseware courseware, String courseId);
}
