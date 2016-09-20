package team.dx.classroom.web.student.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.Experiment;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.ExperimentService;
import team.dx.classroom.utils.CompilerUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class StudentExperimentServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	
	private CourseService cService = ObjectFactory.getInstance().createObject(CourseService.class);
	private ExperimentService eService = ObjectFactory.getInstance().createObject(ExperimentService.class);
	
	public void getAddCourseExperiments(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String studentId = getUserId(request, response);
		List<Course> courses = cService.getStudentAllCoursesExperiments(studentId);
		request.setAttribute("courses", courses);
		request.getRequestDispatcher("/student/experiments.jsp").forward(request, response);
		
	}
	
	public void viewCourseExperiments(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String courseId = request.getParameter("courseId");
		List<Experiment> experiments = eService.getExperiments(courseId);

		Course course = new Course();
		course.setId(courseId);
		course.setExperiments(experiments);

		request.setAttribute("course", course);
		request.getRequestDispatcher("/course/experiment.jsp").forward(request, response);
	
	}
	
	/* 学生做/ 查看实验 */
	public void doExperiment(HttpServletRequest request,
				HttpServletResponse response) throws Exception {

		String experimentId = request.getParameter("experimentId");

		Experiment experiment = eService.getExperiment(experimentId);
		Integer score = eService.getExperimentScore(experimentId, getUserId(request, response));

		request.setAttribute("score", score);
		request.setAttribute("experiment", experiment);
		request.getRequestDispatcher("/student/experiment.jsp").forward(request, response);
	}
	
	/** 检查代码  */
	public void checkCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			//上传检查文件
			
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

			String path = null;
			String filename = null;

			// Process the uploaded items
			for (FileItem item : items) {
				if (!item.isFormField()) {
				   
					filename = item.getName();
				    path = getServletContext().getRealPath("/WEB-INF/classes");
					File uploadedFile = new File(path + File.separator + filename);
					item.write(uploadedFile);
				}
				
			}
			
			//得到实验的输入参数
			String experimentId = request.getParameter("experimentId");
			Experiment experiment = eService.getExperiment(experimentId);

			//评分
			boolean isRight = markScore(path, filename, experiment);
			int score = 0;
			
			if (isRight) {
				score = 100;
			}
			
			String userId = getUserId(request, response);
			
			//查看是否已经上传过
			boolean isupload = eService.isUplod(experimentId, userId);
			
			//插入/更新 成绩
			//插入过-->更新
			if (isupload) {
				eService.updateExperimentScore(experimentId, userId, score);
			} else { //没有过-->插入
				eService.insertExperimentScore(experimentId, userId, score);
			}
			//删除上传java文件和生成的class文件
			String uri = path + File.separator + filename;
			deleteFile(uri);
			uri = path + File.separator + filename.split("\\.")[0] + ".class";
			deleteFile(uri);

			// 操作成功后返回
			request.setAttribute("score", score);
			response.sendRedirect(request.getContextPath()
								+ "/servlet/StudentExperimentServlet?method=doExperiment&experimentId="+experimentId);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "未知异常: " + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}

	}
	
	private void deleteFile(String uri) {
		File file = new File(uri);
		if (file.exists()) {
			file.delete();
		}
	}
	

	public boolean markScore(String path, String fileName, Experiment experiment) {
		
		String inputString = experiment.getInput();
		String[] inputs = inputString.split("\\_");
		Integer[] inputNums = new Integer[inputs.length];
		
		//0代表字符串，1代替数字，其他留着扩展
		int flag = experiment.getFlag();
		int length = inputs.length;
		Object[] result = null;
		if (flag == 0) {
			
			//inputs本身就是字符串
			result = CompilerUtils.run(path, fileName, inputs);
			
			
		} else if (flag == 1) {
			
			for (int i = 0; i < length; i++) {
				inputNums[i] = new Integer(inputs[i]);
			}
			
			result = CompilerUtils.run(path, fileName, inputNums);
			
		}
		
		String output = experiment.getOutput();
		if (output.equals("" + result[0])) {
			return true;
		} else {
			return false;
		}
		
	}

	
	
	/**
	 * 从session 中获取用户id
	 * 如果不能获取则返回登录页面(将来用filter 实现)
	 * */
	public static String getUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("user");

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return null;
		}

		return user.getId();
	}
	
}
