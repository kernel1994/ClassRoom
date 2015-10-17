package team.dx.classroom.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team.dx.classroom.exception.DaoException;
import team.dx.classroom.utils.JDBCUtils2;

/**
 * 封装了基本的 CRUD 方法, 以供子类继承使用 当前 DAO 直接在方法中获取数据库连接 整个DAO采取DBUtils解决方案 泛型T: 当前 DAO
 * 处理的实体类的类型是什么
 * */
@SuppressWarnings("unchecked")
public class BasicDAO<T> {

	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;

	public BasicDAO() {
		// 获得父类的泛型参数的实际类型(this是指的是当前对象的引用, 对象是子类生成的对象)

		/*
		 * Type: Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
		 * getSuperClass() 获得该类的父类 getGenericSuperclass() 获得该类的带有泛型的父类
		 */
		Type superClass = getClass().getGenericSuperclass();

		// ParameterizedType 参数化类型，即泛型
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;

			// getActualTypeArguments() 获取参数化类型的数组，泛型可能有多个
			Type[] typeArgs = parameterizedType.getActualTypeArguments();

			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
	}

	/**
	 * 该方法封装了 INSERT/ DELETE/ UPDATE 操作
	 * 
	 * @param sql
	 *            SQL语句
	 * @param args
	 *            填充SQL语句的占位符的数据
	 * */
	public void update(String sql, Object... args) {

		try {
			queryRunner.update(JDBCUtils2.getConnection(), sql, args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("出现异常类为: " + clazz.getSimpleName());
		} 
	}

	/**
	 * 返回对应的 T 的一个实例类的对象
	 * 
	 * @param sql
	 *            SQL语句
	 * @param args
	 *            填充SQL语句的占位符的数据
	 * @return 对应的 T 的一个实例类的对象
	 * */
	public T get(String sql, Object... args) {

		try {
			return queryRunner.query(JDBCUtils2.getConnection(), sql,
					new BeanHandler<T>(clazz), args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("出现异常类为: " + clazz.getSimpleName());
		}
	}

	/**
	 * 返回对应的 T 的多个实例类的对象组成的List
	 * 
	 * @param sql
	 *            SQL语句
	 * @param args
	 *            填充SQL语句的占位符的数据
	 * @return 对应的 T 的多个实例类的对象组成的List
	 * */
	public List<T> getForList(String sql, Object... args) {

		try {
				return queryRunner.query(JDBCUtils2.getConnection(), sql, new BeanListHandler<T>(
						clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("出现异常类为: " + clazz.getSimpleName());
		}
	}

	/**
	 * 返回一个字段的值. 例如返回一条student记录中的student_name
	 * 
	 * @param sql
	 *            SQL语句
	 * @param args
	 *            填充SQL语句的占位符的数据
	 * @return 一条记录中的一个字段的值
	 * */
	public <E> E getTheValue(String sql, Object... args) {

		try {
			return queryRunner.query(JDBCUtils2.getConnection(), sql, new ScalarHandler<E>(),
					args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("出现异常类为: " + clazz.getSimpleName());
		}
	}

}
