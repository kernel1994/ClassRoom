package team.dx.classroom.web.student.servlet;

import com.google.gson.Gson;
import team.dx.classroom.dao.TaskDAO;
import team.dx.classroom.domain.*;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.CoursewareService;
import team.dx.classroom.service.HomeWorkService;
import team.dx.classroom.service.TaskService;
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

	private CourseService cService = ObjectFactory.getInstance().createObject(CourseService.class);
	private TaskService tService = ObjectFactory.getInstance().createObject(TaskService.class);
	private CoursewareService cwService = ObjectFactory.getInstance().createObject(CoursewareService.class);
	private HomeWorkService hService = ObjectFactory.getInstance().createObject(HomeWorkService.class);

	@Override
	public int getSuffixLen() {
		
		return ".stu".length();
	}

	/**
	 * 从session 中获取用户id
	 * 如果不能获取则返回登录页面(将来用filter 实现)
	 * */
	private String getUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("user");

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return null;
		}

		return user.getId();
	}

	public void createIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// request.setAttribute("courses", courses);
		request.getRequestDispatcher("/student/index.jsp").forward(request, response);
	}
	
	public void queryCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String courseName = request.getParameter("courseName");
		String teacherName = request.getParameter("teacherName");
		String limitperson = request.getParameter("limitperson");
		String description = request.getParameter("description");

		String studentId = getUserId(request, response);
		
		Map<String, String> args = new HashMap<String, String>();
		
		args.put("courseName", courseName);
		args.put("teacherName", teacherName);
		args.put("limitperson", limitperson);
		args.put("description", description);
		
		List<Course> courses = cService.getCourses(args, studentId);
		
		// System.out.println(courses);
		
		request.setAttribute("courses", courses);
		
		request.getRequestDispatcher("/student/choose_course.jsp").forward(request, response);
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

		String studentId = getUserId(request, response);
		
		request.setAttribute("courses", cService.getStudentCourses(studentId));

		request.getRequestDispatcher("/student/student_course.jsp").forward(request, response);
	}

	public void getTeacherCoursesById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String teacherId = request.getParameter("teacherId");

		List<Course> courses = cService.getTeacherCoursesById(teacherId);

		request.setAttribute("courses", courses);

		request.getRequestDispatcher("/student/index.jsp").forward(request, response);
	}

	/* 下面三个方法貌似不应该写在这？值得考虑 */
	public void viewCourseIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String courseId = request.getParameter("courseId");
		Course course = cService.getCourse(courseId);

		request.setAttribute("course", course);
		request.getRequestDispatcher("/course/index.jsp").forward(request, response);
	}

	public void viewCourseTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String courseId = request.getParameter("courseId");
		List<Task> tasks = tService.getCourseTasks(courseId);

		Course course = new Course();
		course.setId(courseId);
		course.setTasks(tasks);

		request.setAttribute("course", course);
		request.getRequestDispatcher("/course/task.jsp").forward(request, response);
	}

	public void viewCoursewares(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String courseId = request.getParameter("courseId");
		List<Courseware> coursewares = cwService.getCoursewares(courseId);

		Course course = new Course();
		course.setId(courseId);
		course.setCoursewares(coursewares);

		request.setAttribute("course", course);
		request.getRequestDispatcher("/course/chapter.jsp").forward(request, response);
	}

	public void viewStudentAllCoursesTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String studentId = getUserId(request, response);
		List<Course> courses = cService.getStudentAllCoursesTasks(studentId);

		request.setAttribute("courses", courses);
		request.getRequestDispatcher("/student/tasks.jsp").forward(request, response);
	}

	/* 学生做/ 查看作业 */
	public void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String taskId = request.getParameter("taskId");
		String studentId = getUserId(request, response);

		HomeWork homeWork = null;
		String path = null;

		// 查到分数是null 则表示没有做
		if (tService.getStudentTaskScore(studentId, taskId) == null) {
			homeWork = hService.getHomeWork(taskId);
			path = "/student/task.jsp";
			request.setAttribute("taskId", taskId);

		} else {
			/* 学生查看自己做的作业 */
			homeWork = hService.getStudentHomeWork(taskId, studentId);
			path = "/student/taskView.jsp";

		}

		request.setAttribute("homeWork", homeWork);
		request.getRequestDispatcher(path).forward(request, response);
	}

	/* 学生提交作业 */
	public void submitTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String data = request.getParameter("data");
		Gson gson = new Gson();
		HashMap<String, String> map = gson.fromJson(data, HashMap.class);

		String taskId = map.get("taskId");

		// 添加学生id。用于存入答案文件标识
		String studentId = getUserId(request, response);
		map.put("studentId", studentId);

		HashMap<String, String> wrong = hService.checkHomework(taskId, map);
		String sWrong = gson.toJson(wrong, HashMap.class);

		// System.out.println("swrong: " + sWrong);
		// System.out.println("stu: " + map);

		/* 禁用缓存 */
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);

		PrintWriter out = response.getWriter();

		out.write(sWrong);
		out.flush();
		out.close();
	}

}


