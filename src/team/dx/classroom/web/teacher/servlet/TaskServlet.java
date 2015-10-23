package team.dx.classroom.web.teacher.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.Task;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.TaskService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/* *
 * 负责处理作业的逻辑控制
 * */
public class TaskServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(
			CourseService.class);
	private TaskService ts = ObjectFactory.getInstance().createObject(
			TaskService.class);

	public void addTaskUI(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String courseId = request.getParameter("courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			request.getRequestDispatcher("/teacher/course/publishtask.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUI未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}

	};

	public void publishTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			System.out.println("ok");
			
			// 得到一个作业资源保存的父路径
			String path = this.getServletContext().getRealPath(
					"/resource/task/homework");
			
			/*------------对作业的描述存取到数据库--------------*/
			// 作业的描述

			Task task = WebUtils
					.request2Bean(request.getParameterMap(), Task.class);
			task.setId(WebUtils.getRandomUUID());
			
			// 上传者
			User uploader = (User) request.getSession().getAttribute("user");
			
			// 作业资源对象
			Resource resource = WebUtils.conver2Resource(task, uploader, path);

			task.setResource(resource);

			/*-------------存取真实的作业--------------*/
			// 封装作业内容
			HomeWork homeWork = WebUtils.request2HomeWork(request);
			
			//得到一个xml作业模板,空内容
			String standardPath = this.getServletContext().getRealPath(
					"/resource/task/homework_standard.xml");
			//作业写入目录
			String desPath =  resource.getUri();
			
			//将作业写进硬盘
			ts.addHomeWork(homeWork, desPath, standardPath);
			
			//将作业描述插入数据库
			ts.addTask(task, request.getParameter("courseId"));
		} catch (Exception e) {
			
		}
		
		
		

	}

}
