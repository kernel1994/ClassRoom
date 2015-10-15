package test.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.Role;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.PersonBusinessService;
import team.dx.classroom.service.TeacherBusinessService;
import team.dx.classroom.utils.JDBCUtils2;

public class TeacherBusinessServiceImplTest {

	TeacherBusinessService tbs = ObjectFactory.getInstance().createObject(TeacherBusinessService.class);
	PersonBusinessService pbs = ObjectFactory.getInstance().createObject(PersonBusinessService.class);
	
	/**
	 * 
	 * 测试获取老师所教的课程
	 * 
	 * */
	@Test
	public void test1() {
		List<Course> list = tbs.getAllCourses("18156e00-876c-4d37-b630-e5dca0ee42b4");
		System.out.println(list);
	}

	/**
	 * 
	 * 添加课程
	 * 
	 * */
	@Test
	public void test2() {
		
		User teacher = pbs.getUser("955d47e2-f1f3-459e-a10c-df2edcbe6a0c");
		Course course = new Course();
		course.setId("asd541as1dfasdf");
		course.setName("数值分析");
		course.setLimitperson(60);
		course.setDescription("听不懂的课程");
		
		course.setTeacher(teacher);
		
		tbs.addCourses(course);
		JDBCUtils2.commitTransaction();
	}
}
