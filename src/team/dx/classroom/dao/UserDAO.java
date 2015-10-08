package team.dx.classroom.dao;

import team.dx.classroom.domain.User;

public interface UserDAO {

	public User getUser(String condition, Object ... args);
	
	public void updateUser(User user);
	
	public void deleteUser(String id);
	
	public void addUser(User user);
}
