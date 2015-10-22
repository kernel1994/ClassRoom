package team.dx.classroom.dao;

import team.dx.classroom.domain.HomeWork;

public interface HomeWorkDAO {
	void add(HomeWork homeWork, String path, String standardPath);
}
