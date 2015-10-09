package test.junit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;

public class UserDAOImplTest {
	UserDAO uDao = ObjectFactory.getInstance().createObject(UserDAO.class);
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void add() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setNick("����");
		user.setEmail("dengjili@qq.com");
		user.setPassword("12346");
		user.setName("��С��");
		user.setGender("��");
		user.setBirthday(new Date());
		user.setAddress("�Ĵ�_�ɶ�_˫��");
		user.setDescription("�������ǰ�ĵ�һ��ͬѧ");
		
		uDao.addUser(user);
//		-------------->pass junit test
	}
}
