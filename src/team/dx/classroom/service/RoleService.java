package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.domain.Role;

public interface RoleService {
	/**
	 * �õ����н�ɫ
	 *
	 * */
	List<Role> getAllRoles();
	
	/**
	 * �õ���ӽ�ɫ
	 * 
	 * */
	void addRole(Role role);
	/**
	 * �����û�ע���У��û������ɫ
	 * @param role_id role����
	 * */
	Role getRole(String role_id);

}
