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
 * ����Կμ����߼���Ϣ
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

			// �μ�
			List<Courseware> coursewares = cws.getCoursewares(courseId);
			request.setAttribute("coursewares", coursewares);

			request.getRequestDispatcher("/teacher/manager/listcourseware.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message",
					"CoursewareServletδ֪�쳣��" + e.getMessage());
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
			request.setAttribute("message", "TaskServlet_addTaskUIδ֪�쳣");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void publishCourseware(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String courseId = (String) request.getSession()
				.getAttribute("courseId");
		Course course = cs.getCourse(courseId);
		// �ϴ���
		User uploader = (User) request.getSession().getAttribute("user");

		try {
			// �����Ƿ����ļ��ϴ�������enctype="multipart/form-data"
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart == false) {
				// ��ʾʧ����Ϣ��Ȼ������ת�����ҳ��
				request.setAttribute("message", "�����ļ��ϴ�");
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

			// �μ���Ϣ
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
					// ��ȡ·��
					String storePath = getServletContext().getRealPath(
							"/resource/resourceware");
					// �����ϴ�����������Դ��װ����

					Resource resource = processUploadedFile(item, storePath,
							course, courseware, uploader);
					courseware.setResource(resource);
				}
			}

			cws.addCourseware(courseware, courseId);

			request.setAttribute("message", "�ϴ��ļ��ɹ���");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("message", "δ֪�쳣: " + e.getMessage());
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
