package team.dx.classroom.service;

import team.dx.classroom.domain.Role;
import team.dx.classroom.domain.User;

public interface PersonBusinessService {
	
	User findUserIsExist(String nick);
	
	/**
	 * �����û���¼��ͨ����������������ݿ��ѯ��Ӧ���û�
	 * @param email �û���¼������
	 * @param password ����
	 * @return ����ɹ�����User ���󣬷��򷵻�null
	 * */
	User getUser(String email, String password);

	/**
	 * �����û�ע�ᣬ���û���Ϣ��װ��user
	 * @param user user��װ�н�ɫrole��Ϣ
	 * */
	void addUser(User user);

	/**
	 * �����û�ע���У��û������ɫ
	 * @param role_id role����
	 * */
	Role getRole(String role_id);
}
