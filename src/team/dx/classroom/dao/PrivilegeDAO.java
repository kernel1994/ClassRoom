package team.dx.classroom.dao;

import team.dx.classroom.domain.Privilege;

public interface PrivilegeDAO {

	public Privilege getPrivilege(String condition, Object ... args);
	
	public void updatePrivilege(Privilege privilege);
	
	public void deletePrivilege(String id);
	
	public void addPrivilege(Privilege privilege);
}
