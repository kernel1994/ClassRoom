package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.domain.Course;

public interface TeacherBusinessService {

	/**
	 * 用于老师登陆成功后，显示属于该老师的课程
	 * @param teacherId 老师的id
	 * @return 如果成功返回Course集合对象，否则返回null
	 * */
	List<Course> getAllCourses(String teacherId);
	
	/**
	 * 老师添加课程
	 * @param course course对象
	 * 
	 * */
	void addCourses(Course course);
	
}
