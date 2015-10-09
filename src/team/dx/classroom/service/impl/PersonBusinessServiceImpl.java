package team.dx.classroom.service.impl;

import java.util.List;

import team.dx.classroom.dao.PrivilegeDAO;
import team.dx.classroom.dao.RoleDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.PersonBusinessService;

public class PersonBusinessServiceImpl implements PersonBusinessService {

	UserDAO uDao = ObjectFactory.getInstance().createObject(UserDAO.class);
	RoleDAO rDao = ObjectFactory.getInstance().createObject(RoleDAO.class);
	PrivilegeDAO pDao = ObjectFactory.getInstance().createObject(PrivilegeDAO.class);
	
	@Override
	public User findUserIsExist(String nick) {
		String condition = "select id from user where nick = ?";
		List<User> users = uDao.getUsers(condition, nick);
		if (users.size() == 0) {
			return null;
		}
		
		return users.get(0);
	}

}
