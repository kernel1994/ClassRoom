package team.dx.classroom.web.teacher.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.Task;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.HomeWorkService;
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
	private HomeWorkService hs = ObjectFactory.getInstance().createObject(
			HomeWorkService.class);

	public void listTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);

			// 作业
			List<Task> tasks = ts.getCourseTasks(courseId);
			request.setAttribute("tasks", tasks);

			request.getRequestDispatcher("/teacher/manager/listtask.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUI未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

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
			// 得到一个作业资源保存的父路径
			String path = this.getServletContext().getRealPath(
					"/resource/task/homework");

			/*------------对作业的描述存取到数据库--------------*/
			// 作业的描述

			Task task = WebUtils.request2Bean(request.getParameterMap(),
					Task.class);
			task.setId(WebUtils.getRandomUUID());

			// 课程
			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			Course course = cs.getCourse(courseId);

			// 上传者
			User uploader = (User) request.getSession().getAttribute("user");

			// 作业资源对象
			Resource resource = WebUtils.conver2Resource(task, uploader,
					course, path);
			task.setResource(resource);

			/*-------------存取真实的作业--------------*/
			// 封装作业内容
			HomeWork homeWork = WebUtils.request2HomeWork(request);

			// 得到一个xml作业模板,空内容
			String standardPath = this.getServletContext().getRealPath(
					"/resource/task/homework_standard.xml");
			// 作业写入目录
			String desPath = resource.getUri();

			// 将作业写进硬盘
			ts.addHomeWork(homeWork, desPath, standardPath);

			// 将作业描述插入数据库
			ts.addTask(task, course.getId());

			// 操作成功后返回
			response.sendRedirect(request.getContextPath()
					+ "/servlet/TaskServlet?method=listTask");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "未知异常!<br/>" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}

	}

	public void checkTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String taskId = request.getParameter("taskid");
			HomeWork homeWork = hs.getHomeWork(taskId);
			request.setAttribute("homeWork", homeWork);
			request.getRequestDispatcher("/teacher/manager/checkatask.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message",
					"checkTask异常!<br/>" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void deleteTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String taskId = request.getParameter("taskid");

			// 先得到路径，不然数据库中记录删除了就没有记录了
			String pathname = ts.getTaskPath(taskId);

			// 删除数据库中记录
			ts.deleteTask(taskId);

			// 删除真实存储
			WebUtils.deleteFile(pathname);

			// 回显
			listTask(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "TaskServlet_deleteTask未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void taskProgress(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			// 作业id
			String taskId = request.getParameter("taskid");
			// 课程id
			String courseId = (String) request.getSession().getAttribute(
					"courseId");

			// 描述作业
			Task task = ts.getTask(taskId);
			// 找出已经做了作业的所有学生
			// 提交已经批改的作业的所有学生
			List<User> noNeedMarkGradeStudents = ts
					.getNoNeedMarkGradeStudent(taskId);
			// 提交未批改的作业的所有学生
			List<User> needMarkGradeStudents = ts
					.getNeedMarkGradeStudent(taskId);

			// 找出还还没做了作业的所有学生
			List<User> notHaveFinishStudents = ts.getNotHaveFinishStudent(
					courseId, taskId);
			
			if (noNeedMarkGradeStudents != null) {
				request.setAttribute("noNeedMarkGradeStudentCount",
						noNeedMarkGradeStudents.size());
			} else {
				request.setAttribute("noNeedMarkGradeStudentCount", 0);
			}

			if (needMarkGradeStudents != null) {
				request.setAttribute("needMarkGradeStudentCount",
						needMarkGradeStudents.size());
			} else {
				request.setAttribute("needMarkGradeStudentCount", 0);
			}

			if (notHaveFinishStudents != null) {
				request.setAttribute("notHaveFinishStudentCount",
						notHaveFinishStudents.size());
			} else {
				request.setAttribute("notHaveFinishStudentCount", 0);
			}

			request.setAttribute("task", task);
			request.setAttribute("noNeedMarkGradeStudents", noNeedMarkGradeStudents);
			request.setAttribute("needMarkGradeStudents", needMarkGradeStudents);
			request.setAttribute("notHaveFinishStudents", notHaveFinishStudents);

			request.getRequestDispatcher("/teacher/manager/taskprogress.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message",
					"taskProgress异常!<br/>" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void markGrade(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			HomeWorkService hService = ObjectFactory.getInstance()
					.createObject(HomeWorkService.class);

			String taskId = request.getParameter("taskId");
			String studentId = request.getParameter("studentId");

			HomeWork homeWork = hService.getStudentHomeWork(taskId, studentId);

			request.setAttribute("homeWork", homeWork);

			request.getRequestDispatcher("/teacher/manager/marktask.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message",
					"markGrade异常!<br/>" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}

	}

}
