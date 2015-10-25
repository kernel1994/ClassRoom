package team.dx.classroom.dao;

import team.dx.classroom.domain.HomeWork;

public interface HomeWorkDAO {

	void add(HomeWork homeWork, String path, String standardPath);

	/**
	 * 读取Homework 对象。参数注意添加文件协议file:///
	 * */
	public HomeWork get(String path);
}
