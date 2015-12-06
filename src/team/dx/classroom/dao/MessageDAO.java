package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Message;

public interface MessageDAO {

	public List<Message> getMessages(String condition, Object ... args);

	public  Message getMessage(String condition, Object... args);

	public void updateMessage(Message message);
	
	public void deleteMessage(String id);
	
	public  void addMessage(Message message);
}
