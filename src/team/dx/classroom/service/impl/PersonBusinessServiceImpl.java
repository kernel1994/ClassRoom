package team.dx.classroom.service.impl;

import java.util.List;

import team.dx.classroom.dao.PrivilegeDAO;
import team.dx.classroom.dao.RoleDAO;
import team.dx.classroom.dao.ThirdPartyCommonDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.Privilege;
import team.dx.classroom.domain.Role;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.PersonBusinessService;

public class PersonBusinessServiceImpl implements PersonBusinessService {

	UserDAO uDAO = ObjectFactory.getInstance().createObject(UserDAO.class);
	RoleDAO rDAO = ObjectFactory.getInstance().createObject(RoleDAO.class);
	PrivilegeDAO pDAO = ObjectFactory.getInstance().createObject(PrivilegeDAO.class);
	
	ThirdPartyCommonDAO tDAO = ObjectFactory.getInstance().createObject(ThirdPartyCommonDAO.class);
	
	@Override
	public boolean findUserIsExist(String nick) {
		String condition = "select id from user where nick = ?";
		User user = uDAO.getUser(condition, nick);
		return user == null ? true : false;
	}
	
	@Override
	public User getUser(String email, String password) {
		
		User user = null;
		
		// �Ȳ�ѯuser ��ȡuser_id
		String userCondition = "SELECT * FROM user WHERE email = ? AND password = ?";
		
		// ���Ϊ�鵽��getUsers() ���ؿռ���
		user = uDAO.getUser(userCondition, email, password);
		
		// Ϊ���򷵻�null
		if (user == null) {
			return null;
		}
		
		// Ȼ�����user_id ��ȡrole ����
		String roleCondition = "SELECT * FROM role WHERE id = (SELECT role_id FROM user_role WHERE user_id = ?)";
		Role role = rDAO.getRole(roleCondition, user.getId());
		
		if (role == null) {
			return user;
		}
		
		// �ٸ���role_id ��ȡprivilege ����
		String privilegeCondition = "SELECT * FROM privilege WHERE id = (SELECT privilege_id FROM role_privilege WHERE role_id = ?)";
		List<Privilege> privileges = pDAO.getPrivileges(privilegeCondition, role.getId());
		
		// �����ϳ�������User ����
		role.setPrivileges(privileges);
		user.setRole(role);
		
		return user;
	}

	@Override
	public void addUser(User user) {
		
		try {
			if (user == null) {
				return;
			}
			uDAO.addUser(user);
			
			Role role = user.getRole();
			if (role == null) {
				return;
			}
			/*--------����user��role��ϵ---------*/
			tDAO.updateUserRole("insert", user.getId(), role.getId());
			
			List<Privilege> privileges = role.getPrivileges();
			if (privileges == null || privileges.size() == 0) {
				return;
			}
			
			pDAO.addPrivilege(privileges);
			/*--------����role��privileges��ϵ---------*/
			for (Privilege privilege : privileges) {
				tDAO.updateRolePrivilege("insert", privilege.getId(), role.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Role getRole(String role_id) {
		
		String condition = "select * from role where id = ?";
		
		return rDAO.getRole(condition, role_id);
	}

	@Override
	public List<Role> getAllRoles() {
		String condition = "select * from role";
		return rDAO.getRoles(condition);
	}

	@Override
	public User getUser(String id) {
		
		String condition = "select * from user where id = ?";
		
		return uDAO.getUser(condition, id);
	}

}
