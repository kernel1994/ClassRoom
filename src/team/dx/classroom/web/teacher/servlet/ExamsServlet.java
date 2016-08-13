package team.dx.classroom.web.teacher.servlet;

import team.dx.classroom.domain.*;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.ExamsService;
import team.dx.classroom.service.TaskService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExamsServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private ExamsService es = ObjectFactory.getInstance().createObject(
			ExamsService.class);
	private CourseService cs = ObjectFactory.getInstance().createObject(
			CourseService.class);
	private TaskService ts = ObjectFactory.getInstance().createObject(
			TaskService.class);
	
	public void listExams(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			request.getRequestDispatcher("/teacher/exams/index.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "listExams未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}
	
	public void addExams(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			// 封装作业内容
			HomeWork homeWork = WebUtils.request2HomeWork(request);
			
			//添加到数据库
			if (homeWork.getSelects().size() != 0 &&
					homeWork.getTrueOrFalses().size() != 0 &&
					homeWork.getShortQuestions().size() != 0 ) {
				es.addExams(homeWork);
			} else {
				//没有录入数据处理
			}
			
			String flag = request.getParameter("flag");
			
			//生成题
			if ("1".equalsIgnoreCase(flag)) {
				// 作业的描述
				Task task = WebUtils.request2Bean(request.getParameterMap(), Task.class);
				task.setId(WebUtils.getRandomUUID());
				
				// 得到一个 资源保存的父路径
				String path = this.getServletContext().getRealPath(
						"/resource/task/homework");
				// 课程id
				String courseId = (String) request.getSession().getAttribute(
						"courseId");
				// 上传者
				User uploader = (User) request.getSession().getAttribute("user");
				Course course = cs.getCourse(courseId);
				
				// 作业资源对象
				Resource resource = WebUtils.conver2Resource(task, uploader,
						course, path);
				task.setResource(resource);
				// 将作业描述插入数据库
				ts.addTask(task, course.getId());
				
				//生成具体题（xml）
				
				/*-------------存取真实的作业--------------*/
				// 得到一个xml作业模板,空内容
				String standardPath = this.getServletContext().getRealPath(
						"/resource/task/homework_standard.xml");
				String desPath = resource.getUri();
				
				// 将作业写进硬盘
				ts.addHomeWork(homeWork, desPath, standardPath);
				
				request.setAttribute("message", "操作成功");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			}
			
		} catch (Exception e) {
			request.setAttribute("message", "listExams未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}
	
	public void createExam(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			// 封装作业内容
			String chapter = request.getParameter("chapter");
			String degree = request.getParameter("degree");
			String knowledgepoint = request.getParameter("knowledgepoint");
			String type = request.getParameter("type");
			String examcount = request.getParameter("examcount");
			
			HomeWork homeWork = es.createExams(chapter,degree,knowledgepoint,type,examcount);
			/**************************************************************************/
			// 作业的描述
			Task task = WebUtils.request2Bean(request.getParameterMap(), Task.class);
			task.setId(WebUtils.getRandomUUID());
			
			// 得到一个 资源保存的父路径
			String path = this.getServletContext().getRealPath(
					"/resource/task/homework");
			// 课程id
			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			// 上传者
			User uploader = (User) request.getSession().getAttribute("user");
			Course course = cs.getCourse(courseId);
			
			// 作业资源对象
			Resource resource = WebUtils.conver2Resource(task, uploader,
					course, path);
			task.setResource(resource);
			// 将作业描述插入数据库
			ts.addTask(task, course.getId());
			
			//生成具体题（xml）
			
			/*-------------存取真实的作业--------------*/
			// 得到一个xml作业模板,空内容
			String standardPath = this.getServletContext().getRealPath(
					"/resource/task/homework_standard.xml");
			String desPath = resource.getUri();
			
			// 将作业写进硬盘
			ts.addHomeWork(homeWork, desPath, standardPath);
			
			request.setAttribute("homeWork", homeWork);
			request.getRequestDispatcher("/teacher/exams/listexam.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("message", "listExams未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			e.printStackTrace();
		}
	}

}