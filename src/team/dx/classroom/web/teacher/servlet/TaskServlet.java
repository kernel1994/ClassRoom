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
 * ��������ҵ���߼�����
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
			request.setAttribute("message", "TaskServlet_addTaskUIδ֪�쳣");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}

	};

	public void publishTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			System.out.println("ok");
			
			// �õ�һ����ҵ��Դ����ĸ�·��
			String path = this.getServletContext().getRealPath(
					"/resource/task/homework");
			
			/*------------����ҵ��������ȡ�����ݿ�--------------*/
			// ��ҵ������

			Task task = WebUtils
					.request2Bean(request.getParameterMap(), Task.class);
			task.setId(WebUtils.getRandomUUID());
			
			// �ϴ���
			User uploader = (User) request.getSession().getAttribute("user");
			
			// ��ҵ��Դ����
			Resource resource = WebUtils.conver2Resource(task, uploader, path);

			task.setResource(resource);

			/*-------------��ȡ��ʵ����ҵ--------------*/
			// ��װ��ҵ����
			HomeWork homeWork = WebUtils.request2HomeWork(request);
			
			//�õ�һ��xml��ҵģ��,������
			String standardPath = this.getServletContext().getRealPath(
					"/resource/task/homework_standard.xml");
			//��ҵд��Ŀ¼
			String desPath =  resource.getUri();
			
			//����ҵд��Ӳ��
			ts.addHomeWork(homeWork, desPath, standardPath);
			
			//����ҵ�����������ݿ�
			ts.addTask(task, request.getParameter("courseId"));
		} catch (Exception e) {
			
		}
		
		
		

	}

}
