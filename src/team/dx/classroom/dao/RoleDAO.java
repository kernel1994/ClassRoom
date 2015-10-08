package team.dx.classroom.dao;

import team.dx.classroom.domain.Role;

public interface RoleDAO {

	public Role getRole(String condition, Object ... args);
	
	public void updateRole(Role role);
	
	public void deleteRole(String id);
	
	public void addRole(Role role);
}
