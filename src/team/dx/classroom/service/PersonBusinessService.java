package team.dx.classroom.service;

import team.dx.classroom.domain.Role;
import team.dx.classroom.domain.User;

public interface PersonBusinessService {
	
	User findUserIsExist(String nick);
	
	/**
	 * 用于用户登录，通过邮箱和密码来数据库查询对应的用户
	 * @param email 用户登录的邮箱
	 * @param password 密码
	 * @return 如果成功返回User 对象，否则返回null
	 * */
	User getUser(String email, String password);

	/**
	 * 用于用户注册，将用户信息封装成user
	 * @param user user中装有角色role信息
	 * */
	void addUser(User user);

	/**
	 * 用于用户注册中，用户分配角色
	 * @param role_id role主键
	 * */
	Role getRole(String role_id);
}
