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
 * 用于登录时在数据库中查找并组合然后返回User对象
 * */
public class LoginService {
	
	private UserDAO userDAO = new UserDAOImpl();
	private RoleDAO roleDAO = new RoleDAOImpl();
	private PrivilegeDAO privilegeDAO = new PrivilegeDAOImpl(); 

	public User getUser(String email, String password) {
		
		User user = null;
		
		// 先查询user 获取user_id
		String userCondition = "SELECT * FROM user WHERE email = ? AND password = ?";
		
		// 如果为查到则getUsers() 返回空集合
		List<User> users = userDAO.getUsers(userCondition, email, password);
		
		// 为空则返回null
		if (users.size() == 0) {
			return null;
		}
		
		user = users.get(0);
		
		// 然后根据user_id 获取role 对象
		String roleCondition = "SELECT * FROM role WHERE id = (SELECT role_id FROM user_role WHERE user_id = ?)";
		Role role = roleDAO.getRole(roleCondition, user.getId());
		
		// 再根据role_id 获取privilege 对象
		String privilegeCondition = "SELECT * FROM privilege WHERE id = (SELECT privilege_id FROM role_privilege WHERE role_id = ?)";
		List<Privilege> privileges = privilegeDAO.getPrivileges(privilegeCondition, role.getId());
		
		// 最后组合成完整的User 对象
		role.setPrivileges(privileges);
		user.setRole(role);
		
		return user;
	}
}
