package team.dx.classroom.dao;

import team.dx.classroom.domain.HomeWork;

public interface HomeWorkDAO {

	void add(HomeWork homeWork, String path, String standardPath);

	/**
	 * ��ȡHomework ���󡣲���ע������ļ�Э��file:///
	 * */
	public HomeWork get(String path);
}
