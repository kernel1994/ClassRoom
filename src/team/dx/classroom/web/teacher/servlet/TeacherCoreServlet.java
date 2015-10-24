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
 * ������ѧ�����л������߼�
 * 
 * */
public class TeacherCoreServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(CourseService.class);
	private TaskService ts = ObjectFactory.getInstance().createObject(TaskService.class);
	
	//��ʾ��ʦ�����γ̵�������
	public void coreIndexUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			/*---------------����һ�ž���Ŀγ�-----------------------*/
			String courseId = request.getParameter("id");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			
			//��װҳ������Ҫ������
			//��ҵ
			List<Task> tasks = ts.getCourseTasks(courseId);
			request.setAttribute("tasks", tasks);
			
			//��ʾҳ��
			
			request.getRequestDispatcher("/teacher/manager/managerindex.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "���ִ���: " + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
	}
}
