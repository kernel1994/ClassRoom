package team.dx.classroom.web.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.User;
import team.dx.classroom.exception.DaoException;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;

/* *
 * 显示教师主页UI
 * */
public class TeacherIndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CourseService cbs = ObjectFactory.getInstance().createObject(CourseService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			/*-----------教师登陆成功后，显示自己所教的课程------------*/
			String teacherId = ((User)request.getSession().getAttribute("user")).getId();
			List<Course> courses = cbs.getAllCourses(teacherId);
			request.setAttribute("courses", courses);
			request.getRequestDispatcher("/teacher/index.jsp").forward(request, response);
		} catch (DaoException e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
