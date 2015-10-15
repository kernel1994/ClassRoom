package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.domain.Course;

public interface TeacherBusinessService {

	/**
	 * ������ʦ��½�ɹ�����ʾ���ڸ���ʦ�Ŀγ�
	 * @param teacherId ��ʦ��id
	 * @return ����ɹ�����Course���϶��󣬷��򷵻�null
	 * */
	List<Course> getAllCourses(String teacherId);
	
	/**
	 * ��ʦ��ӿγ�
	 * @param course course����
	 * 
	 * */
	void addCourses(Course course);
	
}
