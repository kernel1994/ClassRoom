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
		user.setNick("文章");
		user.setEmail("dengjili@qq.com");
		user.setPassword("12346");
		user.setName("王小明");
		user.setGender("男");
		user.setBirthday(new Date());
		user.setAddress("四川_成都_双流");
		user.setDescription("这是我们班的第一个同学");
		
		uDao.addUser(user);
//		-------------->pass junit test
	}
}
