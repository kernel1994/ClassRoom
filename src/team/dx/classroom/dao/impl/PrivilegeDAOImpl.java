package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.PrivilegeDAO;
import team.dx.classroom.domain.Privilege;

public class PrivilegeDAOImpl extends BasicDAO<Privilege> implements PrivilegeDAO{

	@Override
	public List<Privilege> getPrivileges(String condition, Object... args) {
		
		return getForList(condition, args);
	}

	@Override
	public void updatePrivilege(Privilege privilege) {
		String sql = "UPDATE privilege SET name = ?, description = ? WHERE id =?";
		
		update(sql, privilege.getName(), privilege.getDescription(), privilege.getId());
	}

	@Override
	public void deletePrivilege(String id) {
		String sql = "DELETE FROM privilege WHERE id = ?";
		
		update(sql, id);
	}

	@Override
	public void addPrivilege(Privilege privilege) {
		String sql = "INSERT INTO privilege (id, name, description) VALUES (?, ?, ?)";
		
		update(sql, privilege.getId(), privilege.getName(), privilege.getDescription());
	}

}
