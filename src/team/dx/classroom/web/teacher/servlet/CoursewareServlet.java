package team.dx.classroom.web.teacher.servlet;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.Courseware;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;
import team.dx.classroom.service.CoursewareService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/**
 * 处理对课件的逻辑消息
 * 
 * */
public class CoursewareServlet extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private CourseService cs = ObjectFactory.getInstance().createObject(
			CourseService.class);
	private CoursewareService cws = ObjectFactory.getInstance().createObject(
			CoursewareService.class);

	public void listCourseware(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);

			// 课件
			List<Courseware> coursewares = cws.getCoursewares(courseId);
			request.setAttribute("coursewares", coursewares);

			request.getRequestDispatcher("/teacher/manager/listcourseware.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message",
					"CoursewareServlet未知异常：" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void addCoursewareUI(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			Course course = cs.getCourse(courseId);
			request.setAttribute("course", course);
			request.getRequestDispatcher(
					"/teacher/course/publishcourseware.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("message", "TaskServlet_addTaskUI未知异常");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void publishCourseware(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String courseId = (String) request.getSession()
				.getAttribute("courseId");
		Course course = cs.getCourse(courseId);
		// 上传者
		User uploader = (User) request.getSession().getAttribute("user");

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
			Courseware courseware = new Courseware();
			courseware.setId(WebUtils.getRandomUUID());
			// Process the uploaded items
			for (FileItem item : items) {

				if (item.isFormField()) {

					String name = item.getFieldName();
					String value = item.getString("utf-8");
					PropertyUtils.setSimpleProperty(courseware, name, value);

					item.getInputStream().close();
				} else {
					// 存取路径
					String storePath = getServletContext().getRealPath(
							"/resource/resourceware");
					// 处理上传，并返回资源封装对象

					Resource resource = processUploadedFile(item, storePath,
							course, courseware, uploader);
					courseware.setResource(resource);
				}
			}

			cws.addCourseware(courseware, courseId);

			request.setAttribute("message", "上传文件成功！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("message", "未知异常: " + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	private Resource processUploadedFile(FileItem item, String storePath,
			Course course, Courseware courseware, User uploader) {
		try {
			String fileName = item.getName();
			String uri = storePath + File.separator + uploader.getId() + "_" + course.getName()
					+ "_"+ fileName;
			File uploadedFile = new File(uri);
			item.write(uploadedFile);

			Resource resource = new Resource(WebUtils.getRandomUUID(),
					fileName, uri, new Date(), courseware.getDescription(),
					uploader);
			return resource;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
