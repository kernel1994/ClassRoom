package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.User;

public interface UserDAO {

	public List<User> getUsers(String condition, Object ... args);
	
	public User getUser(String condition, Object ... args);
	
	public void updateUser(User user);
	
	public void deleteUser(String id);
	
	public void addUser(User user);
}
