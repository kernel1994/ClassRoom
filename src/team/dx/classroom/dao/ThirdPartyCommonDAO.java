package team.dx.classroom.dao;

public interface ThirdPartyCommonDAO {

	/**
	 * �÷�����װ�� INSERT/ DELETE/ UPDATE ����
	 * 
	 * @param sql SQL���
	 * @param args ���SQL����ռλ��������
	 * */
	public abstract void update(String sql, Object... args);

	/**
	 * �Ա�courseware_review ���� INSERT/ DELETE/ <del>UPDATE</del> ����
	 * @param type �ַ��� INSERT ��  DELETE �� <del>UPDATE</del> (�����ִ�Сд) �е�һ��
	 * @param args ���������У�<br />insert ����˳���� �γ�ID ����ID<br />
	 * delete ����ֻ��Ҫ ����ID
	 * */
	public abstract void updateCoursewareReview(String type, Object... args);

	/**
	 * �Ա�role_privilege ���� INSERT/ DELETE/ UPDATE ����������update ����ֻ���޸�privilege_id
	 * @param type �ַ��� INSERT ��  DELETE �� UPDATE (�����ִ�Сд) �е�һ��
	 * @param args ���������У�<br />insert ��Ҫ����������˳���� Ȩ��ID ��ɫID<br />
	 * delete ��Ҫ����������˳���� Ȩ��ID ��ɫID<br />
	 * update ��Ҫ����������˳���� Ȩ��ID ��ɫID
	 * */
	public abstract void updateRolePrivilege(String type, Object... args);

	/**
	 * �Ա�student_course ���� INSERT/ DELETE/ UPDATE ����������update ����ֻ���޸ķ���
	 * @param type �ַ��� INSERT ��  DELETE �� UPDATE (�����ִ�Сд) �е�һ��
	 * @param args ���������У�<br />insert ��Ҫ 3 ��������˳���� ѧ��ID �γ�ID ����<br />
	 * delete ��Ҫ 2 ��������˳���� ѧ��ID �γ�ID<br />
	 * update ��Ҫ 3 ��������˳���� ���� ѧ��ID �γ�ID
	 * */
	public abstract void updateStudentCourse(String type, Object... args);

	/**
	 * �Ա�task_review ���� INSERT/ DELETE/ <del>UPDATE</del> ����
	 * @param type �ַ��� INSERT ��  DELETE �� <del>UPDATE</del> (�����ִ�Сд) �е�һ��
	 * @param args ���������У�<br />insert ��Ҫ 2 ��������˳���� ��ҵID ����ID<br />
	 * delete ��Ҫ 2 ��������˳���� ��ҵID ����ID
	 * */
	public abstract void updateTaskReview(String type, Object... args);

	/**
	 * �Ա�user_role ���� INSERT/ DELETE/ <del>UPDATE</del> ����
	 * @param type �ַ��� INSERT ��  DELETE �� <del>UPDATE</del> (�����ִ�Сд) �е�һ��
	 * @param args ���������У�<br />insert ��Ҫ 2 ��������˳���� �û�ID ��ɫID<br />
	 * delete ��Ҫ 2 ��������˳���� �û�ID ��ɫID
	 * */
	public abstract void updateUserRole(String type, Object... args);

	/**
	 * �Ա�user_task ���� INSERT/ DELETE/ UPDATE ����
	 * @param type �ַ��� INSERT ��  DELETE �� UPDATE (�����ִ�Сд) �е�һ��
	 * @param args ���������У�<br />insert ��Ҫ 3 ��������˳���� ѧ��ID ��ҵID ����<br />
	 * delete ��Ҫ 2 ��������˳���� ѧ��ID ��ҵID<br />
	 * update ��Ҫ 3 ��������˳���� ���� ѧ��ID ��ҵID
	 * */
	public abstract void updateUserTask(String type, Object... args);

}