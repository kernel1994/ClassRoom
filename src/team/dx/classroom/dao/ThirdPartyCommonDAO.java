package team.dx.classroom.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import team.dx.classroom.exception.DaoException;
import team.dx.classroom.utils.JDBCUtils;

public class ThirdPartyCommonDAO {

	private QueryRunner queryRunner = new QueryRunner();
	private Connection connection = null;
	
	/**
	 * 该方法封装了 INSERT/ DELETE/ UPDATE 操作
	 * 
	 * @param sql SQL语句
	 * @param args 填充SQL语句的占位符的数据
	 * */
	public void update(String sql, Object... args) {

		try {
			connection = JDBCUtils.getConnection();
			queryRunner.update(connection, sql, args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("出现异常类为: ");
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}
	
	
	/**
	 * 对表courseware_review 进行 INSERT/ DELETE/ <del>UPDATE</del> 操作
	 * @param type 字符串 INSERT 或  DELETE 或 <del>UPDATE</del> (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 参数顺序是 课程ID 评论ID<br />
	 * delete 参数只需要 评论ID
	 * */
	public void updateCoursewareReview(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO courseware_review (courseware_id, review_id) VALUES (?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM courseware_review WHERE review_id = ?";
		} else if (type.equals("update")) {
			// 暂时不提供此方法，预留以后变更
		} else {
			throw new DaoException("请指定正确的type 值");
		}
		
		update(sql, args);
	}
	
	/**
	 * 对表courseware_review 进行 INSERT/ DELETE/ UPDATE 操作。其中update 操作只能修改privilege_id
	 * @param type 字符串 INSERT 或  DELETE 或 UPDATE (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要两个参数，顺序是 权限ID 角色ID<br />
	 * delete 需要两个参数，顺序是 权限ID 角色ID<br />
	 * update 需要两个参数，顺序是 权限ID 角色ID
	 * */
	public void updateRolePrivilege(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO role_privilege (privilege_id, role_id) VALUES (?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM role_privilege WHERE privilege_id = ? AND role_id = ?";
		} else if (type.equals("update")) {
			sql = "UPDATE role_privilege SET privilege_id = ? WHERE privilege_id = ? AND role_id = ?";
		} else {
			throw new DaoException("请指定正确的type 值");
		}
		
		update(sql, args);
	}
	
	
	/**
	 * 对表courseware_review 进行 INSERT/ DELETE/ UPDATE 操作。其中update 操作只能修改分数
	 * @param type 字符串 INSERT 或  DELETE 或 UPDATE (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 3 个参数，顺序是 学生ID 课程ID 分数<br />
	 * delete 需要 2 个参数，顺序是 学生ID 课程ID<br />
	 * update 需要 3 个参数，顺序是 分数 学生ID 课程ID
	 * */
	public void updateStudentCourse(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO student_course (student_id, course_id, score) VALUES (?, ?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM student_course WHERE student_id = ? AND course_id = ?";
		} else if (type.equals("update")) {
			sql = "UPDATE student_course SET score = ? WHERE student_id = ? AND course_id = ?";
		} else {
			throw new DaoException("请指定正确的type 值");
		}
		
		update(sql, args);
	}
	
	
	/**
	 * 对表courseware_review 进行 INSERT/ DELETE/ <del>UPDATE</del> 操作
	 * @param type 字符串 INSERT 或  DELETE 或 <del>UPDATE</del> (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 2 个参数，顺序是 作业ID 评论ID<br />
	 * delete 需要 2 个参数，顺序是 作业ID 评论ID
	 * */
	public void updateTaskReview(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO task_review (task_id, review_id) VALUES (?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM task_review WHERE task_id = ? AND review_id = ?";
		} else if (type.equals("update")) {
			// 暂不提供此方法
		} else {
			throw new DaoException("请指定正确的type 值");
		}
		
		update(sql, args);
	}
	
	/**
	 * 对表courseware_review 进行 INSERT/ DELETE/ <del>UPDATE</del> 操作
	 * @param type 字符串 INSERT 或  DELETE 或 <del>UPDATE</del> (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 2 个参数，顺序是 用户ID 角色ID<br />
	 * delete 需要 2 个参数，顺序是 用户ID 角色ID
	 * */
	public void updateUserRole(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM user_role WHERE user_id = ? AND role_id = ?";
		} else if (type.equals("update")) {
			// 暂不提供此方法
		} else {
			throw new DaoException("请指定正确的type 值");
		}
		
		update(sql, args);
	}
	
	
	/**
	 * 对表courseware_review 进行 INSERT/ DELETE/ UPDATE 操作
	 * @param type 字符串 INSERT 或  DELETE 或 UPDATE (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 3 个参数，顺序是 学生ID 作业ID 分数<br />
	 * delete 需要 2 个参数，顺序是 学生ID 作业ID<br />
	 * update 需要 3 个参数，顺序是 分数 学生ID 作业ID
	 * */
	public void updateUserTask(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO user_task (user_id, task_id, score) VALUES (?, ?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM user_task WHERE user_id = ? AND task_id = ?";
		} else if (type.equals("update")) {
			sql = "UPDATE user_task SET score = ? WHERE user_id = ? AND task_id = ?";
		} else {
			throw new DaoException("请指定正确的type 值");
		}
		
		update(sql, args);
	}
	
}
