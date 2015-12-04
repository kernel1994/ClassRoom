package team.dx.classroom.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import team.dx.classroom.dao.CoursewareDAO;
import team.dx.classroom.domain.Courseware;
import team.dx.classroom.factory.ObjectFactory;

public class CoursewareServiceImplTest {

	private CoursewareDAO cwDAO = ObjectFactory.getInstance().createObject(CoursewareDAO.class);
	
	@Test
	public void testGetCourseware() {
		Courseware courseware = cwDAO.getCourseware("select * from courseware where id = ?", "894655a1-12f9-44f4-b5c1-e8baf1f7cf8b");
		System.out.println(courseware);
	}

}
