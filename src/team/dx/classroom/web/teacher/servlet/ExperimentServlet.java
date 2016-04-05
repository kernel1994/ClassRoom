package team.dx.classroom.web.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Experiment;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.ExperimentService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

public class ExperimentServlet  extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	
	private ExperimentService es = ObjectFactory.getInstance().createObject(ExperimentService.class);

	//列举出所有实验，注意：可考虑分页实现
	public void listExperiment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			List<Experiment> experiments = es.getExperiments(courseId);
			request.setAttribute("experiments", experiments);

			request.getRequestDispatcher("/teacher/experiment/listexperiment.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUI未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}
	
	//添加实验UI
	public void addExperimentUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/teacher/experiment/addexperiment.jsp").forward(
				request, response);
	}
	
	//添加实验逻辑实现
	public void addExperiment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//实验的描述
			Experiment experiment = WebUtils.request2Bean(request.getParameterMap(), Experiment.class);
			experiment.setId(WebUtils.getRandomUUID());
			//输入
			String input = request2Input(request, "input");
			String output = request2Input(request, "output");
			experiment.setInput(input);
			experiment.setOutput(output);
			
			//课程id
			String courseId = (String) request.getSession().getAttribute("courseId");
			es.addExperiment(experiment, courseId);
			// 操作成功后返回
			response.sendRedirect(request.getContextPath()
				+ "/servlet/ExperimentServlet?method=listExperiment");
			
		} catch (Exception e) {
			request.setAttribute("message", "未知异常!<br/>" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			throw new RuntimeException(e);
		}
		
	}

	private String request2Input(HttpServletRequest request, String type) {
		String[] inputs = request.getParameterValues(type);
		if (inputs == null) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		for (String string : inputs) {
			sb.append(string + "_");
		}
		String result = sb.substring(0, sb.length() - 1);
		
		return result;
	}
	
	
	
	
}
