package test.junit;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import team.dx.classroom.utils.XmlUtils;

public class ServletTestXml extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*String path = this.getServletContext().getRealPath("/resource/task/homework_standard.xml");
		try {
			Document document = XmlUtils.parse(path);
			XmlUtils.write(document);
			System.out.println("ok");
		} catch (DocumentException e) {
			e.printStackTrace();
		}*/
		String pat1h = this.getServletContext().getRealPath("/resource/task/homework");
		System.out.println(pat1h);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
