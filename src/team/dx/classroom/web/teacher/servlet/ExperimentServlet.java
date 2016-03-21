package team.dx.classroom.web.teacher.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.Courseware;
import team.dx.classroom.domain.Experiment;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.Task;
import team.dx.classroom.domain.User;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

public class ExperimentServlet  extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;

	//列举出所有实验，注意：可考虑分页实现
	public void listExperiment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

		/*	String courseId = (String) request.getSession().getAttribute(
					"courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);

			// 作业
			List<Task> tasks = ts.getCourseTasks(courseId);
			request.setAttribute("tasks", tasks);*/

			request.getRequestDispatcher("/teacher/experiment/listexperiment.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUI未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}
	
	//添加实验
	public void addExperimentUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/teacher/experiment/addexperiment.jsp").forward(
				request, response);
	}
	
	public void addExperiment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//课程号
		String courseId = (String) request.getSession()
				.getAttribute("courseId");
		// 上传者
		User uploader = (User) request.getSession().getAttribute("user");
		
		request.getParameterMap()

		try {
			// 检测表单是否有文件上传的属性enctype="multipart/form-data"
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart == false) {
				// 显示失败消息，然后再跳转到添加页面
				request.setAttribute("message", "这是文件上传");
				request.getRequestDispatcher("message").forward(request,
						response);
				return;
			}
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			File repository = new File(this.getServletContext().getRealPath(
					"/resource/temp"));
			factory.setRepository(repository);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// upload.setSizeMax(1024*1024*5);

			// Parse the request
			List<FileItem> items = upload.parseRequest(request);

			// 课件信息
			Experiment experiment = new Experiment();
			experiment.setId(WebUtils.getRandomUUID());
			// Process the uploaded items
			for (FileItem item : items) {

				if (item.isFormField()) {

					String name = item.getFieldName();
					String value = item.getString("utf-8");
					//PropertyUtils.setSimpleProperty(courseware, name, value);
					System.out.println(value);
					

					item.getInputStream().close();
				} else {
					// 存取路径
					String storePath = getServletContext().getRealPath(
							"/resource/resourceware");
					// 处理上传，并返回资源封装对象

				}
			}

			

			// 操作成功后返回
			//response.sendRedirect(request.getContextPath()
			//					+ "/servlet/CoursewareServlet?method=listCourseware");
		} catch (Exception e) {
			request.setAttribute("message", "未知异常: " + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}
	
	
	
	
}
