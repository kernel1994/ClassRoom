package team.dx.classroom.web.teacher.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.Courseware;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.CoursewareService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/**
 * ����Կμ����߼���Ϣ
 * 
 * */
public class CoursewareServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(
			CourseService.class);
	private CoursewareService cws = ObjectFactory.getInstance().createObject(
			CoursewareService.class);

	public void listCourseware(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);

			// �μ�
			List<Courseware> coursewares = cws.getCoursewares(courseId);
			request.setAttribute("coursewares", coursewares);

			request.getRequestDispatcher("/teacher/manager/listcourseware.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message",
					"CoursewareServletδ֪�쳣��" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void addCoursewareUI(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			request.getRequestDispatcher(
					"/teacher/course/publishcourseware.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUIδ֪�쳣");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void publishCourseware(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			// �����Ƿ����ļ��ϴ�������
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart == false) {
				// ��װ������Ϣ
				Courseware courseware = WebUtils.request2Bean(
						request.getParameterMap(), Courseware.class);
				request.setAttribute("courseware", courseware);

				// ��ʾʧ����Ϣ��Ȼ������ת�����ҳ��
				request.setAttribute("message", "δ�ϴ��ļ�����ȷ�Ϻ����ϴ�");
				request.getRequestDispatcher(
						"/teacher/course/publishcourseware.jsp").forward(
						request, response);
				return;
			}
			
			System.out.println("ok");
		} catch (Exception e) {
			request.setAttribute("message", "δ֪�쳣: " + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}
}
