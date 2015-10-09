package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.User;

public class UserDAOImpl extends BasicDAO<User>implements UserDAO {

	@Override
	public List<User> getUsers(String condition, Object... args) {

		return getForList(condition, args);
	}

	@Override
	public void updateUser(User user) {

		String sql = "UPDATE user SET nick = ?, email = ?, password = ?, name = ?, "
				+ "gender = ?, birthday = ?, address = ?, description = ? WHERE id = ?";

		update(sql, user.getNick(), user.getEmail(), user.getPassword(), user.getName(), user.getGender(),
				user.getBitthday(), user.getAddress(), user.getDescription(), user.getId());
	}

	@Override
	public void deleteUser(String id) {

		String sql = "DELETE FROM user WHERE id = ?";

		update(sql, id);
	}

	@Override
	public void addUser(User user) {

		String sql = "INSERT INTO user (id, nick, email, password, name, gender, birthday, address, desctiption) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		update(sql, user.getId(), user.getNick(), user.getEmail(), user.getPassword(), user.getName(), user.getGender(),
				user.getBitthday(), user.getAddress(), user.getDescription());
	}

}
