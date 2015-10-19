package team.dx.classroom.service.impl;

import java.util.List;

import team.dx.classroom.dao.RoleDAO;
import team.dx.classroom.domain.Role;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.RoleService;

public class RoleServiceImpl implements RoleService {
	
	private RoleDAO rDAO = ObjectFactory.getInstance().createObject(RoleDAO.class);
	
	@Override
	public List<Role> getAllRoles() {
		String condition = "select * from role";
		return rDAO.getRoles(condition);
	}

	@Override
	public void addRole(Role role) {
		rDAO.addRole(role);
	}
	
	@Override
	public Role getRole(String role_id) {
		
		String condition = "select * from role where id = ?";
		
		return rDAO.getRole(condition, role_id);
	}

}
