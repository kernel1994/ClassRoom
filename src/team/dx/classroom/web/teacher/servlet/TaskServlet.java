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
import team.dx.classroom.service.TaskService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/* *
 * ��������ҵ���߼�����
 * */
public class TaskServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(
			CourseService.class);
	private TaskService ts = ObjectFactory.getInstance().createObject(
			TaskService.class);

	public void listTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String courseId = (String) request.getSession().getAttribute("courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			
			//��ҵ
			List<Task> tasks = ts.getCourseTasks(courseId);
			request.setAttribute("tasks", tasks);
			

			request.getRequestDispatcher("/teacher/manager/listtask.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUIδ֪�쳣");
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
			request.setAttribute("message", "TaskServlet_addTaskUIδ֪�쳣");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}

	};

	public void publishTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			// �õ�һ����ҵ��Դ����ĸ�·��
			String path = this.getServletContext().getRealPath(
					"/resource/task/homework");

			/*------------����ҵ��������ȡ�����ݿ�--------------*/
			// ��ҵ������

			Task task = WebUtils.request2Bean(request.getParameterMap(),
					Task.class);
			task.setId(WebUtils.getRandomUUID());
			
			//�γ�
			String courseId = (String) request.getSession().getAttribute("courseId");
			Course course = cs.getCourse(courseId);

			// �ϴ���
			User uploader = (User) request.getSession().getAttribute("user");

			// ��ҵ��Դ����
			Resource resource = WebUtils.conver2Resource(task, uploader, course, path);
			task.setResource(resource);

			/*-------------��ȡ��ʵ����ҵ--------------*/
			// ��װ��ҵ����
			HomeWork homeWork = WebUtils.request2HomeWork(request);

			// �õ�һ��xml��ҵģ��,������
			String standardPath = this.getServletContext().getRealPath(
					"/resource/task/homework_standard.xml");
			// ��ҵд��Ŀ¼
			String desPath = resource.getUri();

			// ����ҵд��Ӳ��
			ts.addHomeWork(homeWork, desPath, standardPath);

			// ����ҵ�����������ݿ�
			ts.addTask(task, course.getId());

			// �����ɹ��󷵻�
			response.sendRedirect(request.getContextPath()
					+ "/servlet/TeacherCoreServlet?method=coreIndexUI&id="
					+ request.getSession().getAttribute("courseId"));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "δ֪�쳣!<br/>" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}

	}

}
