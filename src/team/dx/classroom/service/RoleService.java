package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.domain.Role;

public interface RoleService {
	/**
	 * 得到所有角色
	 *
	 * */
	List<Role> getAllRoles();
	
	/**
	 * 得到添加角色
	 * 
	 * */
	void addRole(Role role);
	/**
	 * 用于用户注册中，用户分配角色
	 * @param role_id role主键
	 * */
	Role getRole(String role_id);

}
