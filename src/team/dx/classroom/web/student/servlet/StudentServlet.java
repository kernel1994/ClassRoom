package team.dx.classroom.web.student.servlet;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.web.servlet.MethodInvokeServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServlet extends MethodInvokeServlet {
	private static final long serialVersionUID = 1L;
	
	CourseService cService = ObjectFactory.getInstance().createObject(CourseService.class);
	
	@Override
	public int getSuffixLen() {
		
		return ".stu".length();
	}
	
	public void createIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		queryCourse(request, response);
	}
	
	public void queryCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String courseName = request.getParameter("courseName");
		String teacherName = request.getParameter("teacherName");
		String limitperson = request.getParameter("limitperson");
		String description = request.getParameter("description");

		User user = (User)request.getSession().getAttribute("user");

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		String studentId = user.getId();
		
		Map<String, String> args = new HashMap<String, String>();
		
		args.put("courseName", courseName);
		args.put("teacherName", teacherName);
		args.put("limitperson", limitperson);
		args.put("description", description);
		
		List<Course> courses = cService.getCourses(args, studentId);
		
		// System.out.println(courses);
		
		request.setAttribute("courses", courses);
		
		request.getRequestDispatcher("/student/index.jsp").forward(request, response);
	}
	
	/**
	 * 选择课程的Ajax 方法
	 * */
	public void chooseCourseAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			/* 禁用缓存 */
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", -1);
			
			String courseId = request.getParameter("courseId");
			String studentId = request.getParameter("studentId");
			
			cService.chooseCourse(studentId, courseId);
			PrintWriter out = response.getWriter();
			
			out.write("OK");
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * 退选课程的Ajax 方法
	 * */
	public void unchooseCourseAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 禁用缓存 */
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String courseId = request.getParameter("courseId");
		String studentId = request.getParameter("studentId");
		
		cService.unchooseCourse(studentId, courseId);
		
		PrintWriter out = response.getWriter();
		
		out.write("OK");
		out.flush();
		out.close();
	}
	
	public void getStudentCourses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("user");

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		String studentId = user.getId();
		
		request.setAttribute("courses", cService.getStudentCourses(studentId));

		request.getRequestDispatcher("/student/index.jsp").forward(request, response);
	}

	public void getTeacherCoursesById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String teacherId = request.getParameter("teacherId");

		List<Course> courses = cService.getTeacherCoursesById(teacherId);

		request.setAttribute("courses", courses);

		request.getRequestDispatcher("/student/index.jsp").forward(request, response);
	}

	public void viewCourseIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String courseId = request.getParameter("courseId");

		Course course = cService.getCourse(courseId);

		request.setAttribute("course", course);
		request.getRequestDispatcher("/course/index.jsp").forward(request, response);
	}
}


