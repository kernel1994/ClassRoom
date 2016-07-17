package team.dx.classroom.service.impl;

import team.dx.classroom.dao.*;
import team.dx.classroom.domain.*;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseService {

	private UserDAO uDAO = ObjectFactory.getInstance().createObject(UserDAO.class);
	private CourseDAO cDAO = ObjectFactory.getInstance().createObject(CourseDAO.class);
	private TaskDAO tDAO = ObjectFactory.getInstance().createObject(TaskDAO.class);
	private ExperimentDAO eDAO = ObjectFactory.getInstance().createObject(ExperimentDAO.class);
	private ThirdPartyCommonDAO tpDAO = ObjectFactory.getInstance().createObject(ThirdPartyCommonDAO.class);

	private TaskService tService = ObjectFactory.getInstance().createObject(TaskService.class);
	private CoursewareService cwService = ObjectFactory.getInstance().createObject(CoursewareService.class);
	private ExperimentService eService = ObjectFactory.getInstance().createObject(ExperimentService.class);
	private ReviewService rService = ObjectFactory.getInstance().createObject(ReviewService.class);
	private AnnouncementService aService = ObjectFactory.getInstance().createObject(AnnouncementService.class);

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
		User teacher = uDAO.getUser(sqlT, course.getId());
		course.setTeacher(teacher);

		List<Task> tasks = tService.getCourseTasks(courseId);
		course.setTasks(tasks);

		List<Courseware> coursewares = cwService.getCoursewares(courseId);
		course.setCoursewares(coursewares);
		
		//实验
		List<Experiment> experiments = eService.getExperiments(courseId);
		course.setExperiments(experiments);

		List<Review> reviews = rService.getCourseReviews(courseId);
		course.setReviews(reviews);

		// 公告
		List<Announcement> announcements = aService.getCourseAnnouncements(courseId);
		course.setAnnouncements(announcements);

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

		return cDAO.getStudentCourses(studentId);
	}

	@Override
	public void chooseCourse(String studentId, String courseId) throws RuntimeException {
		
		tpDAO.updateStudentCourse("insert", studentId, courseId, 0);
	}

	@Override
	public void unchooseCourse(String studentId, String courseId) {
		
		tpDAO.updateStudentCourse("delete", studentId, courseId);
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

	
	@Override
	public void deleteCourses(String conrseId) {
		cDAO.deleteCourse(conrseId);
		
	}

	@Override
	public void updateCourses(Course course) {
		cDAO.updateCourse(course);
	}

	@Override
	public List<User> getAllStudent(String courseId) {
		String sql = "select user.* from user,student_course where student_course.student_id = user.id and student_course.course_id = ?;";
		return uDAO.getUsers(sql, courseId);
	}
	
	@Override
	public List<Course> getStudentAllCoursesTasks(String studentId) {

		String sql = "select score from user_task where user_id = ? and task_id = ?";
		List<Course> courses = getStudentCourses(studentId);

		for (Course course : courses) {
			Course courseTemp = getCourse(course.getId());
			// 只需要放入task 要显式赋值，才能实现深拷贝
			course.setTasks(courseTemp.getTasks());

			for (Task task : course.getTasks()) {
				Integer score = tDAO.getScore(sql, studentId, task.getId());
				task.setScore(score);
			}
		}

		return courses;
	}


	@Override
	public List<Course> getStudentAllCoursesExperiments(String studentId) {
		
		String sql = "select score from user_experiment where user_id = ? and experiment_id = ?";
		List<Course> courses = getStudentCourses(studentId);
		
		for (Course course : courses) {
			Course courseTemp = getCourse(course.getId());
			// 只需要放入task 要显式赋值，才能实现深拷贝
			course.setExperiments(courseTemp.getExperiments());

			for (Experiment experiment : course.getExperiments()) {
				Integer score = eDAO.getScore(sql, studentId, experiment.getId());
				experiment.setScore(score);
			}
		}

		return courses;
	}

	@Override
	public List<Course> getStudentAllCoursesAnnouncements(String studentId) {
		List<Course> courses = getStudentCourses(studentId);

		for (Course course : courses) {
			Course courseTemp = getCourse(course.getId());
			// 只需要放入task 要显式赋值，才能实现深拷贝
			course.setAnnouncements(courseTemp.getAnnouncements());
		}

		return courses;
	}
}
