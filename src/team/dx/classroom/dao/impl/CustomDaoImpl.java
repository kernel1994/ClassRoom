package team.dx.classroom.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team.dx.classroom.utils.JDBCUtils2;

public class CustomDaoImpl {

	private QueryRunner queryRunner = new QueryRunner();

	public Object getScore(String sql, Object... args) {

		try {

			return queryRunner.query(JDBCUtils2.getConnection(), sql,
					new ScalarHandler<Object>(), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
