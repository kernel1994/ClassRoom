package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Privilege;

public interface PrivilegeDAO {

	/**
	 * 返回privilege对象组成的List
	 * @param condition String类型的查询条件，就是对应的sql语句
	 * @param args 任意多个任意类型的查询参数
	 * @return privilege对象组成的List
	 * */
	public List<Privilege> getPrivileges(String condition, Object ... args);
	
	public void updatePrivilege(Privilege privilege);
	
	public void deletePrivilege(String id);
	
	public void addPrivilege(Privilege privilege);
}
