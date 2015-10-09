package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Privilege;

public interface PrivilegeDAO {

	/**
	 * ����privilege������ɵ�List
	 * @param condition String���͵Ĳ�ѯ���������Ƕ�Ӧ��sql���
	 * @param args �������������͵Ĳ�ѯ����
	 * @return privilege������ɵ�List
	 * */
	public List<Privilege> getPrivileges(String condition, Object ... args);
	
	public void updatePrivilege(Privilege privilege);
	
	public void deletePrivilege(String id);
	
	public void addPrivilege(Privilege privilege);
}
