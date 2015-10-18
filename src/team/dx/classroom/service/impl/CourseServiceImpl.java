package team.dx.classroom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import team.dx.classroom.dao.CourseDAO;
import team.dx.classroom.dao.ThirdPartyCommonDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CourseService;

public class CourseServiceImpl implements CourseService {

	private UserDAO uDAO = ObjectFactory.getInstance().createObject(UserDAO.class);
	private CourseDAO cDAO = ObjectFactory.getInstance().createObject(CourseDAO.class);
	
	private ThirdPartyCommonDAO tDAO = ObjectFactory.getInstance().createObject(ThirdPartyCommonDAO.class);

	@Override
	public Course getCourse(String courseId) {

		String sqlC = "SELECT * FROM course WHERE id = ?";
		Course course = cDAO.getCourse(sqlC, courseId);

		String sqlT = "select * from user "
				+ "where id = ("
					+ "select teacher_id "
					+ "from course as c "
					+ "where c.id = ?"
				+ ")";
		User user = uDAO.getUser(sqlT, course.getId());

		course.setTeacher(user);

		return course;
	}

	@Override
	public List<Course> getTeacherCourses(Map<String, String> args) {
		
		for (String key : args.keySet()) {
			
			if (args.get(key) == null) {
				args.put(key, "");
			}
			
			args.put(key, "%" + args.get(key) + "%");
			// System.out.println(key + ": " + args.get(key));
		}
		
		String sqlT = "SELECT * FROM user AS u WHERE "
				+ "EXISTS (select * from user_role AS ur "
				+ "WHERE ur.role_id = 'teacher' "
				+ "AND ur.user_id = u.id) "
				+ "AND u.nick LIKE ?";
		
		List<User> users = uDAO.getUsers(sqlT, args.get("teacherName"));
		// System.out.println(users);
		
		
		List<Course> courses = new ArrayList<Course>();
		
		String sqlC = "SELECT * FROM course "
				+ "WHERE name LIKE ? "
				+ "AND CONVERT(limitperson, char) LIKE ? "
				+ "AND description LIKE ? "
				+ "AND teacher_id = ?";
		
		for (User user : users) {
			
			List<Course> tmpCourses = cDAO.getCourses(sqlC,
					args.get("courseName"), 
					args.get("limitperson"),
					args.get("description"),
					user.getId());
			for (Course c : tmpCourses) {
				c.setTeacher(user);
			}
			
			smallList2BigList(courses, tmpCourses);
			// System.out.println(courses);
		}
		
		return courses;
	}
	
	private void smallList2BigList(List<Course> big, List<Course> small) {
		
		for (Course e : small) {
			big.add(e);
		}
	}

	@Override
	public List<Course> getStudentCourses(String studentId) {
		
		String sqlC = "select * from course as c "
				+ "where c.id "
				+ "in ("
					+ "select course_id "
					+ "from student_course as sc "
					+ "where sc.student_id = ?"
				+ ")";
		
		List<Course> courses = cDAO.getCourses(sqlC, studentId);
		
		/* f**k */
		String sqlT = "select * from user "
				+ "where id = ("
					+ "select teacher_id "
					+ "from course as c "
					+ "where c.id = ?"
				+ ")";
		
		for (Course c : courses) {
			User teacher = uDAO.getUser(sqlT, c.getId());
			c.setTeacher(teacher);
			c.setHaveOwn(1);
		}
		
		return courses;
	}

	@Override
	public void chooseCourse(String studentId, String courseId) throws RuntimeException {
		
		tDAO.updateStudentCourse("insert", studentId, courseId, 0);
	}

	@Override
	public void unchooseCourse(String studentId, String courseId) {
		
		tDAO.updateStudentCourse("delete", studentId, courseId);
	}
	
	/**
	 * FBI WARNING: ��������е�����id �Ƚ�������String ����
	 * 
	 * ΪʲôҪд�����������Ϊ��д�����ʱ�������������֣����б�עϲ���Ĺ��ܡ�<br />
	 * ���û�б�ע�������Ե�����ΰ�ť����ϲ����ע��<br />
	 * ��֮������Ѿ���ע��ϲ����������ΰ�ť��ʾΪ��ע״̬���ٴε����ȡ��ϲ��<br />
	 * �ҵ�ʵ��˼·�ǻ�ȡ�Լ���ӵ�е��б�Ȼ������ʾ���б���бȶԣ�����У������ø���Դ��һ�����ԡ�
	 * Ȼ����JSP����c:if��ǩ�����ж���ʾ
	 * @param course ʵ���飬Ҫ�������õ��б�����
	 * @param hostCourse �����飬Ҫ���в��յ��б�����
	 * */
	private void haveOwn(List<Course> course, List<Course> hostCourse) {
		
		// �������бȶԣ���������
		for (Course hm : hostCourse) {
			for (Course ms : course) {
				if (ms.getHaveOwn() == 0) {
					if (hm.getId().equals(ms.getId())) {
						ms.setHaveOwn(1);
						break;
					} else {
						ms.setHaveOwn(0);
					}
				}
			}
		}

	}

	@Override
	public List<Course> getCourses(Map<String, String> args, String studentId) {
		
		// �Ȼ�ȡ��ѧ������ѡ��
		List<Course> stuCuorses = getStudentCourses(studentId);
		
		// �ٻ��ѯ�����µĿγ�
		List<Course> courses = getTeacherCourses(args);
		
		// �����
		haveOwn(courses, stuCuorses);
		
		return courses;
	}
	

	@Override
	public List<Course> getAllCourses(String teacherId) throws RuntimeException {
			String condition = "select * from course where teacher_id = ?";
			return cDAO.getCourses(condition, teacherId);
	}

	@Override
	public void addCourses(Course course) throws RuntimeException {
		cDAO.addCourse(course);
	}

	@Override
	public Course getCourse(String courseId) {
		String condition = "select * from course where id = ?";
		return cDAO.getCourse(condition, courseId);
	}

	@Override
	public List<Course> getTeacherCoursesById(String teacherId) {

		String conditionT = "select * from user where id = ?";
		User teacher = uDAO.getUser(conditionT, teacherId);

		String conditionC = "select * from course where teacher_id = ?";
		List<Course> courses = cDAO.getCourses(conditionC, teacherId);

		for (Course c : courses) {
			c.setTeacher(teacher);
		}

		return courses;
	}
}
