package team.dx.classroom.web.teacher.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Course;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/* *
 * ������ѧ�����л������߼�
 * 
 * */
public class TeacherCoreServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(CourseService.class);

	//��ʾ��ʦ�����γ̵�������
	public void coreIndexUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			/*---------------����һ�ž���Ŀγ�-----------------------*/
			String courseId = request.getParameter("id");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			
			//��װҳ������Ҫ������
			
			//��ʾҳ��
			
			request.getRequestDispatcher("/teacher/manager/managerindex.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "���ִ���: " + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
	}
}
