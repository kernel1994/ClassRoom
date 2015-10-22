package team.dx.classroom.web.teacher.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Select;
import team.dx.classroom.domain.ShortQuestion;
import team.dx.classroom.domain.Task;
import team.dx.classroom.domain.TrueOrFalse;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/* *
 * 负责处理作业的逻辑控制
 * */
public class TaskServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(CourseService.class);

	public void addTaskUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			String courseId = request.getParameter("courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			request.getRequestDispatcher("/teacher/course/publishtask.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUI未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	};
	
	public void publishTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//作业的描述
		Task task = WebUtils.request2Bean(request.getParameterMap(), Task.class);
		
		//封装作业内容
		HomeWork homeWork = request2HomeWork(request);
		
		System.out.println("ok");
		
		
	}

	//封装在线编辑作业信息
	private HomeWork request2HomeWork(HttpServletRequest request) {
		
		//真实的作业数据
		HomeWork homeWork = new HomeWork();
		
		//选择题
		String[] stitles = request.getParameterValues("stitle");
		String[] sanswersA = request.getParameterValues("sA");
		String[] sanswersB = request.getParameterValues("sB");
		String[] sanswersC = request.getParameterValues("sC");
		String[] sanswersD = request.getParameterValues("sD");
		String[] sdescriptions = request.getParameterValues("sdescription");
		String[] sanswers = request.getParameterValues("sanswer");
		List<Select> selects = WebUtils.conver2Selects(stitles, sanswersA, sanswersB, sanswersC, sanswersD, sdescriptions, sanswers);
		homeWork.setSelects(selects);
		
		//判断题
		String[] ttitles = request.getParameterValues("ttitle");
		String[] tanswers = request.getParameterValues("tanswer");
		String[] tdescriptions = request.getParameterValues("tdescription");
		List<TrueOrFalse> trueOrFalses =  WebUtils.conver2TrueOrFalse(ttitles,tanswers,tdescriptions);
		homeWork.setTrueOrFalses(trueOrFalses);
		
		//简答题
		String[] qtitles = request.getParameterValues("qtitle");
		String[] qdescriptions = request.getParameterValues("qdescription");
		List<ShortQuestion> shortQuestions = WebUtils.conver2ShortQuestion(qtitles,qdescriptions);
		homeWork.setShortQuestions(shortQuestions);
		
		return homeWork;
	}

}
