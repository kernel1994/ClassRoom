package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.MessageDAO;
import team.dx.classroom.domain.Message;

public class MessageDAOImpl extends BasicDAO<Message> implements MessageDAO {

	@Override
	public List<Message> getMessages(String condition, Object... args) {
		try {
			return getForList(condition, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Message getMessage(String condition, Object... args) {
		return get(condition, args);
	}

	@Override
	public void updateMessage(Message message) {
		
	}

	@Override
	public void deleteMessage(String id) {
		//
	}

	@Override
	public void addMessage(Message message) {
		String sql = "INSERT INTO message (src, des, father, type, time, content) VALUES (?, ?, ?, ?, ?, ?)";
		update(sql, message.getSrc(), message.getDes(), message.getFather(), message.getType(), message.getTime(), message.getContent());
	}

}
