package team.dx.classroom.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC �����Ĺ�����
 * */
public class JDBCUtils2 {
	
	// ֻ��Ҫһ������Դ
	private static DataSource dataSource = null;
	/*
	 * ThreadLocal
	 * �����ṩ���ֲ߳̾� (thread-local) ����,�ñ������ص�Ϊget()����û�в�����set()����û��key
	 * ÿ���߳�Ψһ�ľֲ���ʶ��
	 * �����������߳̿�ʼ����ʼ������������
	 * 
	 * */
	private static ThreadLocal<Connection> tlLocal = new ThreadLocal<Connection>();
	
	// ��̬�����ֻ�ܱ�����һ��
	// �� c3p0-config.xml �����õ� configName
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}
	
	/**
	 * ��������Դ��һ�� Connection ����
	 * @return ����Դ��һ�� Connection ����
	 * @throws SQLException 
	 * */
	public static Connection getConnection() {
		
		try {
			Connection connection = tlLocal.get();
			if (connection == null) {
				connection = dataSource.getConnection();
				connection.setAutoCommit(false);
				tlLocal.set(connection);
			}
			connection.setAutoCommit(false);
			return connection;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * �ύ Connection ������
	 * */
	public static void commitTransaction(){
		try{
			Connection conn = tlLocal.get();
			if(conn!=null){
				conn.commit();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �ͷ� Connection ������
	 * */
	public static void closeConnection() {
		
		Connection connection = null;
		
		try {
			connection = tlLocal.get();
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tlLocal.remove();
		}
	}

}
