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
 * 处理对课件的逻辑消息
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

			// 课件
			List<Courseware> coursewares = cws.getCoursewares(courseId);
			request.setAttribute("coursewares", coursewares);

			request.getRequestDispatcher("/teacher/manager/listcourseware.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message",
					"CoursewareServlet未知异常：" + e.getMessage());
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
			request.setAttribute("message", "TaskServlet_addTaskUI未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void publishCourseware(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			// 检测表单是否有文件上传的属性
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart == false) {
				// 封装回显信息
				Courseware courseware = WebUtils.request2Bean(
						request.getParameterMap(), Courseware.class);
				request.setAttribute("courseware", courseware);

				// 显示失败消息，然后再跳转到添加页面
				request.setAttribute("message", "未上传文件，请确认后再上传");
				request.getRequestDispatcher(
						"/teacher/course/publishcourseware.jsp").forward(
						request, response);
				return;
			}
			
			System.out.println("ok");
		} catch (Exception e) {
			request.setAttribute("message", "未知异常: " + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}
}
