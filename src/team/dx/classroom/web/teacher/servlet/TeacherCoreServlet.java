package team.dx.classroom.web.teacher.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.Task;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.TaskService;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/* *
 * 负责与学生进行互动的逻辑
 * 
 * */
public class TeacherCoreServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(CourseService.class);
	private TaskService ts = ObjectFactory.getInstance().createObject(TaskService.class);
	
	//显示老师操作课程的主界面
	public void coreIndexUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			/*---------------进入一门具体的课程-----------------------*/
			String courseId = request.getParameter("id");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			
			//封装页面所需要的数据
			//作业
			List<Task> tasks = ts.getCourseTasks(courseId);
			request.setAttribute("tasks", tasks);
			
			//显示页面
			
			request.getRequestDispatcher("/teacher/manager/managerindex.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "出现错误: " + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
	}
}
