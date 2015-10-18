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
	 * FBI WARNING: 这个方法中的两个id 比较适用于String 类型
	 * 
	 * 为什么要写这个方法：因为我写代码的时候在听网易音乐，它有标注喜欢的功能。<br />
	 * 如果没有标注过，可以点击心形按钮进行喜欢标注。<br />
	 * 反之，如果已经标注了喜欢，则该心形按钮显示为标注状态，再次点击就取消喜欢<br />
	 * 我的实现思路是获取自己已拥有的列表，然后与显示的列表进行比对，如果有，这设置该资源的一个属性。
	 * 然后在JSP中用c:if标签进行判断显示
	 * @param course 实验组，要进行设置的列表数据
	 * @param hostCourse 参照组，要进行参照的列表数据
	 * */
	private void haveOwn(List<Course> course, List<Course> hostCourse) {
		
		// 挨个进行比对，设置属性
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
		
		// 先获取该学生所有选课
		List<Course> stuCuorses = getStudentCourses(studentId);
		
		// 再获查询条件下的课程
		List<Course> courses = getTeacherCourses(args);
		
		// 作标记
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
