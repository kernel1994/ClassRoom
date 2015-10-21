package team.dx.classroom.web.teacher.servlet;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Select;
import team.dx.classroom.domain.Task;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/* *
 * 负责处理作业的逻辑控制
 * */
public class TaskServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(CourseService.class);

	public void addTaskUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			String courseId = request.getParameter("courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			request.getRequestDispatcher("/teacher/course/publishtask.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUI未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	};
	
	public void publishTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//封装表单数据
		HomeWork homeWork = request2HomeWork(request);
		
		System.out.println("ok");
		
		
	}

	private HomeWork request2HomeWork(HttpServletRequest request) {
		//作业的作业
		Task task = WebUtils.request2Bean(request.getParameterMap(), Task.class);
		//真实的作业
		//选择题
		String[] titles = request.getParameterValues("title");
		String[] answersA = request.getParameterValues("A");
		String[] answersB = request.getParameterValues("B");
		String[] answersC = request.getParameterValues("C");
		String[] answersD = request.getParameterValues("D");
		String[] descriptions = request.getParameterValues("description");
		String[] answers = request.getParameterValues("answer");
		List<Select> selects = WebUtils.conver2Selects(titles, answersA, answersB, answersC, answersD, descriptions, answers);
		
		return null;
	}

}
