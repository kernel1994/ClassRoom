package test.junit;

import org.dom4j.DocumentException;
import org.junit.Test;

import team.dx.classroom.utils.XmlUtils;

public class XmlUtilsTest {

	@Test
	public void test() throws DocumentException {
		XmlUtils.parse("file:///Z:\\apache-tomcat-6.0.44\\webapps\\ClassRoom\\resource\\task\\homework\\第二次作业_d4f1ecba-3ece-4472-ad3f-4527b5844849.xml");
	}

}
