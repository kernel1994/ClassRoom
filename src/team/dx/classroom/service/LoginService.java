package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.dao.PrivilegeDAO;
import team.dx.classroom.dao.RoleDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.dao.impl.PrivilegeDAOImpl;
import team.dx.classroom.dao.impl.RoleDAOImpl;
import team.dx.classroom.dao.impl.UserDAOImpl;
import team.dx.classroom.domain.Privilege;
import team.dx.classroom.domain.Role;
import team.dx.classroom.domain.User;

/**
 * ���ڵ�¼ʱ�����ݿ��в��Ҳ����Ȼ�󷵻�User����
 * */
public class LoginService {
	
	private UserDAO userDAO = new UserDAOImpl();
	private RoleDAO roleDAO = new RoleDAOImpl();
	private PrivilegeDAO privilegeDAO = new PrivilegeDAOImpl(); 

	public User getUser(String email, String password) {
		
		User user = null;
		
		// �Ȳ�ѯuser ��ȡuser_id
		String userCondition = "SELECT * FROM user WHERE email = ? AND password = ?";
		
		// ���Ϊ�鵽��getUsers() ���ؿռ���
		List<User> users = userDAO.getUsers(userCondition, email, password);
		
		// Ϊ���򷵻�null
		if (users.size() == 0) {
			return null;
		}
		
		user = users.get(0);
		
		// Ȼ�����user_id ��ȡrole ����
		String roleCondition = "SELECT * FROM role WHERE id = (SELECT role_id FROM user_role WHERE user_id = ?)";
		Role role = roleDAO.getRole(roleCondition, user.getId());
		
		// �ٸ���role_id ��ȡprivilege ����
		String privilegeCondition = "SELECT * FROM privilege WHERE id = (SELECT privilege_id FROM role_privilege WHERE role_id = ?)";
		List<Privilege> privileges = privilegeDAO.getPrivileges(privilegeCondition, role.getId());
		
		// �����ϳ�������User ����
		role.setPrivileges(privileges);
		user.setRole(role);
		
		return user;
	}
}
