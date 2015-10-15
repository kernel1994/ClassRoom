package team.dx.classroom.service;

import java.util.List;
import java.util.Map;

import team.dx.classroom.domain.Course;

public interface CourseService {
	
	/**
	 * ��ȡ��ѯ�����µ���ʦ����Ŀγ̵�ͨ�÷���
	 * @param args Map ���󣬷�װ�˲�ѯ��������(�γ�������ʦ������ѡ����), 
	 * δָ����������ֵΪ���ַ��� ""
	 * @return �����ѯ�����Ŀγ�List
	 * */
	public List<Course> getTeacherCourses(Map<String, String> args);
	
	/**
	 * ��ȡ��ѯ�����µ���ʦ����Ŀγ̵ķ�������ע�˵�¼�ĸ�ѧ���Ƿ��Ѿ�ѡ����
	 * @param args Map ���󣬷�װ�˲�ѯ��������, δָ����������ֵΪ���ַ��� ""
	 * @param studentId ��ǲ���ѧ��id
	 * @return �����ѯ�����Ŀγ�List
	 * */
	public List<Course> getCourses(Map<String, String> args, String studentId);

	/**
	 * ��ȡ��ѯ�����µ�ѧ��ѡ��Ŀγ�
	 * @param studentId ѧ����id
	 * @return �����ѯ�����Ŀγ�List
	 * */
	public List<Course> getStudentCourses(String studentId);
	
	/**
	 * ѧ��ѡ��
	 * @param studentId ѧ��id
	 * @param courseId �γ�id
	 * */
	public void chooseCourse(String studentId, String courseId);
	
	/**
	 * ѧ���˿�
	 * @param studentId ѧ��id
	 * @param courseId �γ�id
	 * */
	public void unchooseCourse(String studentId, String courseId);
}
