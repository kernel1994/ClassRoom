package team.dx.classroom.service;

import java.util.List;
import java.util.Map;

import team.dx.classroom.domain.Course;

public interface CourseService {
	
	/**
	 * 获取查询条件下的老师开设的课程的通用方法
	 * @param args Map 对象，封装了查询条件参数(课程名，教师名，限选人数), 
	 * 未指定该条件的值为空字符串 ""
	 * @return 满足查询条件的课程List
	 * */
	public List<Course> getTeacherCourses(Map<String, String> args);
	
	/**
	 * 获取查询条件下的老师开设的课程的方法，标注了登录的该学生是否已经选课了
	 * @param args Map 对象，封装了查询条件参数, 未指定该条件的值为空字符串 ""
	 * @param studentId 标记参照学生id
	 * @return 满足查询条件的课程List
	 * */
	public List<Course> getCourses(Map<String, String> args, String studentId);

	/**
	 * 获取查询条件下的学生选择的课程
	 * @param studentId 学生的id
	 * @return 满足查询条件的课程List
	 * */
	public List<Course> getStudentCourses(String studentId);
	
	/**
	 * 学生选课
	 * @param studentId 学生id
	 * @param courseId 课程id
	 * */
	public void chooseCourse(String studentId, String courseId);
	
	/**
	 * 学生退课
	 * @param studentId 学生id
	 * @param courseId 课程id
	 * */
	public void unchooseCourse(String studentId, String courseId);
}
