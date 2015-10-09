package team.dx.classroom.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC �����Ĺ�����
 * */
public class JDBCUtils {
	
	// ֻ��Ҫһ������Դ
	private static DataSource dataSource = null;
	
	// ��̬�����ֻ�ܱ�����һ��
	// �� c3p0-config.xml �����õ� configName
	static {
		dataSource = new ComboPooledDataSource("classroom");
	}
	
	/**
	 * ��������Դ��һ�� Connection ����
	 * @return ����Դ��һ�� Connection ����
	 * @throws SQLException 
	 * */
	public static Connection getConnection() throws SQLException {
		
		return dataSource.getConnection();
	}
	
	/**
	 * �ͷ� Connection ������
	 * @param connection ��ѽ�ر����ӵ����ݿ����Ӷ���
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
