package test.junit;

import static org.junit.Assert.fail;

import org.junit.Test;

import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.impl.CourseServiceImpl;
import team.dx.classroom.utils.JDBCUtils2;

public class CourseServiceImplTest {

	CourseService cs = new CourseServiceImpl();
	
	@Test
	public void testChooseCourse() {
		cs.chooseCourse("1", "1");
		// JDBCUtils2.commitTransaction();
	}

	@Test
	public void testUnchooseCourse() {
		fail("Not yet implemented");
	}

}
