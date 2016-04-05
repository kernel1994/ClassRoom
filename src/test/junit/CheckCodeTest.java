package test.junit;

import org.junit.Test;

import team.dx.classroom.domain.Experiment;
import team.dx.classroom.web.student.servlet.StudentExperimentServlet;


public class CheckCodeTest {

	@Test
	public void test1() {
		String path = "Z:/apache-tomcat-6.0.44/webapps/FileUpload/upload/";
		String filename = "Hello.java";
		
		new StudentExperimentServlet().markScore(path, filename, new Experiment());
		
	}
	
	@Test
	public void test2() {
			String path = "Z:/apache-tomcat-6.0.44/webapps/ClassRoom/resource/experiment";
			String filename = "Hello.java";

			Object[] args = {12,13};
		
			Object[] result = team.dx.classroom.utils.CompilerUtils.run(path, filename, args);
			
			for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}
			
	}
}
