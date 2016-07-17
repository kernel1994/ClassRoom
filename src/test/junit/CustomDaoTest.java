package test.junit;

import org.junit.Test;

import team.dx.classroom.dao.impl.CustomDaoImpl;

public class CustomDaoTest {
	
	CustomDaoImpl abc =  new CustomDaoImpl();
	
	@Test
	public void test1() {
		String sql = "select score2 from user_task where user_id = ? and task_id = ?";
		
		Object object = abc.getScore(sql, "9d0ca5a3-9a58-4cb2-b746-38f9a6b8fd2c", "6e78e44b-d300-44de-ac37-61facd330044");
		System.out.println(object);
	
		
		abc.getScore(sql, "");
		
		int i = 1;
		int a  = i;
		int j = i + a;
		
		int c = j;
		int d = i + a + j + c;
	}
}
