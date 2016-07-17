package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import team.dx.classroom.dao.impl.ExamsDAOImpl;
import team.dx.classroom.domain.Select;
import team.dx.classroom.utils.JDBCUtils2;

public class ExamsDAOTest {

	@Test
	public void testAddSelect() {
		
		Select select = new Select("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
		new ExamsDAOImpl().addSelect(select);
		JDBCUtils2.commitTransaction();
	}

}
