package team.dx.classroom.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC 操作的工具类
 * */
public class JDBCUtils {
	
	// 只需要一份数据源
	private static DataSource dataSource = null;
	
	// 静态代码块只能被创建一次
	// 在 c3p0-config.xml 中配置的 configName
	static {
		dataSource = new ComboPooledDataSource("classroom");
	}
	
	/**
	 * 返回数据源的一个 Connection 对象
	 * @return 数据源的一个 Connection 对象
	 * @throws SQLException 
	 * */
	public static Connection getConnection() throws SQLException {
		
		return dataSource.getConnection();
	}
	
	/**
	 * 释放 Connection 的连接
	 * @param connection 需呀关闭连接的数据库连接对象
	 * */
	public static void closeConnection(Connection connection) {
		
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
