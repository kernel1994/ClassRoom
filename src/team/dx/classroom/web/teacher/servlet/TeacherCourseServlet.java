package team.dx.classroom.web.teacher.servlet;

import java.io.IOException;
import java.util.List;

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
 * 教师对课程的操作
 * */
public class TeacherCourseServlet extends MethodInvokeServlet2 {
	private static final long serialVersionUID = 1L;

	private CourseService cbs = ObjectFactory.getInstance().createObject(
			CourseService.class);

	/*----------添加UI------------*/
	public void addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/teacher/manager/addcourse.jsp").forward(
				request, response);
	}
	/*----------修改UI------------*/
	public void updateUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到course的主键
		String courseId = request.getParameter("courseid");
		Course course = cbs.getCourse(courseId);
		request.setAttribute("course", course);
		
		request.getRequestDispatcher("/teacher/manager/updatecourse.jsp").forward(
				request, response);
	}
	

	/*----------添加课程------------*/
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 封装course数据
			Course course = WebUtils.request2Bean(request.getParameterMap(),
					Course.class);

			// 未校验course合法性
			course.setId(WebUtils.getRandomUUID());

			// 得到用户teacher
			User teacher = (User) request.getSession().getAttribute("user");
			course.setTeacher(teacher);
			cbs.addCourses(course);
			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/servlet/TeacherIndexServlet?method=getAll");
			request.setAttribute(
					"message",
					"添加课程成功<br/>3秒后跳转这也，如何没有,<a href='/ClassRoom/servlet/TeacherIndexServlet?method=getAll'>点击这里</a>");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "TeacherCourseServlet类add方法异常");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}
	
	/*----------修改课程------------*/
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 封装course数据
			Course course = WebUtils.request2Bean(request.getParameterMap(),
					Course.class);

			// 单独获取id
			course.setId(request.getParameter("id"));

			// 得到用户teacher
			User teacher = (User) request.getSession().getAttribute("user");
			course.setTeacher(teacher);
			cbs.updateCourses(course);
			
			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/servlet/TeacherIndexServlet?method=getAll");
			request.setAttribute(
					"message",
					"更新课程成功<br/>3秒后跳转这也，如何没有,<a href='/ClassRoom/servlet/TeacherIndexServlet?method=getAll'>点击这里</a>");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "TeacherCourseServlet类update方法异常");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	/*----------删除课程------------*/
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 得到course的主键
			String conrseId = request.getParameter("courseid");

			// 删除与该课程相关的资源  未实现??????

			// 删除该课程
			cbs.deleteCourses(conrseId);

			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/servlet/TeacherIndexServlet?method=getAll");
			request.setAttribute(
					"message",
					"删除课程成功<br/>3秒后跳转这也，如何没有,<a href='/ClassRoom/servlet/TeacherIndexServlet?method=getAll'>点击这里</a>");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "TeacherCourseServlet类delete方法异常");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
	
	/*----------管理学生------------*/
	public void managerStudents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 得到course的主键
			String conrseId = request.getParameter("courseid");

			// 得到选了这门课的所有学生   /*-------暂时不考虑分页-------*/
			List<User> students = cbs.getAllStudent(conrseId);
			request.setAttribute("students", students);
			request.getRequestDispatcher("/teacher/manager/managerstudents.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "TeacherCourseServlet类managerStudents方法异常");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}
}
