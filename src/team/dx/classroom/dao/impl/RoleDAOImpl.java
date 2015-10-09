package team.dx.classroom.dao.impl;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.RoleDAO;
import team.dx.classroom.domain.Role;

public class RoleDAOImpl extends BasicDAO<Role> implements RoleDAO {

	@Override
	public Role getRole(String condition, Object... args) {
		
		return get(condition, args);
	}

	@Override
	public void updateRole(Role role) {

		String sql = "UPDATE role SET name = ?, description = ? WHERE id =?";
		
		update(sql, role.getName(), role.getDescription(), role.getId());
	}

	@Override
	public void deleteRole(String id) {

		String sql = "DELETE FROM role WHERE id = ?";
		
		update(sql, id);
	}

	@Override
	public void addRole(Role role) {

		String sql = "INSERT INTO role (id, name, description) VALUES (?, ?, ?)";
		
		update(sql, role.getId(), role.getName(), role.getDescription());
	}

}
