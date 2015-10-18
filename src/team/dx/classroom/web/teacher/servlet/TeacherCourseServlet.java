package team.dx.classroom.web.teacher.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/* *
 * ��ʦ�Կγ̵Ĳ���
 * */
public class TeacherCourseServlet extends MethodInvokeServlet2 {
	private static final long serialVersionUID = 1L;
	
	private CourseService cbs = ObjectFactory.getInstance().createObject(CourseService.class);

	/*----------ת��UI------------*/
	public void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/teacher/manager/addcourse.jsp").forward(request, response);
	}
	
	/*----------��ӿγ�------------*/
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			//��װcourse����
			Course course = WebUtils.request2Bean(request.getParameterMap(), Course.class);
			
			//δУ��course�Ϸ���
			course.setId(WebUtils.getRandomUUID());
			
			//�õ��û�teacher
			User teacher = (User) request.getSession().getAttribute("user");
			course.setTeacher(teacher);
			cbs.addCourses(course);
			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/servlet/TeacherIndexServlet?method=getAll");
			request.setAttribute("message","��ӿγ̳ɹ�<br/>3�����ת��Ҳ�����û��,<a href='/ClassRoom/servlet/TeacherIndexServlet?method=getAll'>�������</a>");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "TeacherCourseServlet��add�����쳣");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
		
	}
}
