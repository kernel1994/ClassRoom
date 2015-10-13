package team.dx.classroom.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import team.dx.classroom.dao.ThirdPartyCommonDAO;
import team.dx.classroom.exception.DaoException;
import team.dx.classroom.utils.JDBCUtils2;

public class ThirdPartyCommonDAOImpl implements ThirdPartyCommonDAO {

	private QueryRunner queryRunner = new QueryRunner();
	private Connection connection = JDBCUtils2.getConnection();
	
	@Override
	public void update(String sql, Object... args) {

		try {
			queryRunner.update(connection, sql, args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("�����쳣��Ϊ: ");
		} 
		
	}
	
	
	@Override
	public void updateCoursewareReview(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO courseware_review (courseware_id, review_id) VALUES (?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM courseware_review WHERE review_id = ?";
		} else if (type.equals("update")) {
			// ��ʱ���ṩ�˷�����Ԥ���Ժ���
		} else {
			throw new DaoException("��ָ����ȷ��type ֵ");
		}
		
		update(sql, args);
	}
	
	@Override
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
			throw new DaoException("��ָ����ȷ��type ֵ");
		}
		
		update(sql, args);
	}
	
	
	@Override
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
			throw new DaoException("��ָ����ȷ��type ֵ");
		}
		
		update(sql, args);
	}
	
	@Override
	public void updateTaskReview(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO task_review (task_id, review_id) VALUES (?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM task_review WHERE task_id = ? AND review_id = ?";
		} else if (type.equals("update")) {
			// �ݲ��ṩ�˷���
		} else {
			throw new DaoException("��ָ����ȷ��type ֵ");
		}
		
		update(sql, args);
	}
	
	@Override
	public void updateUserRole(String type, Object...args) {
		
		type = type.toLowerCase();
		String sql = "";
				
		if (type.equals("insert")) {
			sql = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
		} else if (type.equals("delete")) {
			sql = "DELETE FROM user_role WHERE user_id = ? AND role_id = ?";
		} else if (type.equals("update")) {
			// �ݲ��ṩ�˷���
		} else {
			throw new DaoException("��ָ����ȷ��type ֵ");
		}
		
		update(sql, args);
	}
	
	
	@Override
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
			throw new DaoException("��ָ����ȷ��type ֵ");
		}
		
		update(sql, args);
	}
	
}
