package team.dx.classroom.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC 操作的工具类
 * */
public class JDBCUtils2 {
	
	// 只需要一份数据源
	private static DataSource dataSource = null;
	/*
	 * ThreadLocal
	 * 该类提供了线程局部 (thread-local) 变量,该变量的特点为get()方法没有参数，set()方法没有key
	 * 每个线程唯一的局部标识符
	 * 生命周期随线程开始而开始，结束而结束
	 * 
	 * */
	private static ThreadLocal<Connection> tlLocal = new ThreadLocal<Connection>();
	
	// 静态代码块只能被创建一次
	// 在 c3p0-config.xml 中配置的 configName
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}
	
	/**
	 * 返回数据源的一个 Connection 对象
	 * @return 数据源的一个 Connection 对象
	 * @throws SQLException 
	 * */
	public static Connection getConnection() {
		
		try {
			Connection connection = tlLocal.get();
			if (connection == null) {
				connection = dataSource.getConnection();
			}
	
			connection.setAutoCommit(false);
			tlLocal.set(connection);

			return connection;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 提交 Connection 的链接
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
	
	public static void startTransaction() throws SQLException {
		Connection conn = tlLocal.get();
		// 当前线程上是否已经存在连接
		if (conn == null) {
			conn = dataSource.getConnection();
		}
		// 开启事务
		conn.setAutoCommit(false);
		// 放到当前线程上
		tlLocal.set(conn);
	}

	
	/**
	 * 释放 Connection 的连接
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
