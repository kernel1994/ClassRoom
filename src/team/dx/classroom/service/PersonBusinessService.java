package team.dx.classroom.service;

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
}
