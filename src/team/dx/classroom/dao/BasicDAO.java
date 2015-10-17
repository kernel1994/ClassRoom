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
 * ��װ�˻����� CRUD ����, �Թ�����̳�ʹ�� ��ǰ DAO ֱ���ڷ����л�ȡ���ݿ����� ����DAO��ȡDBUtils������� ����T: ��ǰ DAO
 * �����ʵ�����������ʲô
 * */
@SuppressWarnings("unchecked")
public class BasicDAO<T> {

	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;

	public BasicDAO() {
		// ��ø���ķ��Ͳ�����ʵ������(this��ָ���ǵ�ǰ���������, �������������ɵĶ���)

		/*
		 * Type: Java ����������������͵Ĺ����߼��ӿڡ����ǰ���ԭʼ���͡����������͡��������͡����ͱ����ͻ������͡�
		 * getSuperClass() ��ø���ĸ��� getGenericSuperclass() ��ø���Ĵ��з��͵ĸ���
		 */
		Type superClass = getClass().getGenericSuperclass();

		// ParameterizedType ���������ͣ�������
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;

			// getActualTypeArguments() ��ȡ���������͵����飬���Ϳ����ж��
			Type[] typeArgs = parameterizedType.getActualTypeArguments();

			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
	}

	/**
	 * �÷�����װ�� INSERT/ DELETE/ UPDATE ����
	 * 
	 * @param sql
	 *            SQL���
	 * @param args
	 *            ���SQL����ռλ��������
	 * */
	public void update(String sql, Object... args) {

		try {
			queryRunner.update(JDBCUtils2.getConnection(), sql, args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("�����쳣��Ϊ: " + clazz.getSimpleName());
		} 
	}

	/**
	 * ���ض�Ӧ�� T ��һ��ʵ����Ķ���
	 * 
	 * @param sql
	 *            SQL���
	 * @param args
	 *            ���SQL����ռλ��������
	 * @return ��Ӧ�� T ��һ��ʵ����Ķ���
	 * */
	public T get(String sql, Object... args) {

		try {
			return queryRunner.query(JDBCUtils2.getConnection(), sql,
					new BeanHandler<T>(clazz), args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("�����쳣��Ϊ: " + clazz.getSimpleName());
		}
	}

	/**
	 * ���ض�Ӧ�� T �Ķ��ʵ����Ķ�����ɵ�List
	 * 
	 * @param sql
	 *            SQL���
	 * @param args
	 *            ���SQL����ռλ��������
	 * @return ��Ӧ�� T �Ķ��ʵ����Ķ�����ɵ�List
	 * */
	public List<T> getForList(String sql, Object... args) {

		try {
				return queryRunner.query(JDBCUtils2.getConnection(), sql, new BeanListHandler<T>(
						clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("�����쳣��Ϊ: " + clazz.getSimpleName());
		}
	}

	/**
	 * ����һ���ֶε�ֵ. ���緵��һ��student��¼�е�student_name
	 * 
	 * @param sql
	 *            SQL���
	 * @param args
	 *            ���SQL����ռλ��������
	 * @return һ����¼�е�һ���ֶε�ֵ
	 * */
	public <E> E getTheValue(String sql, Object... args) {

		try {
			return queryRunner.query(JDBCUtils2.getConnection(), sql, new ScalarHandler<E>(),
					args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("�����쳣��Ϊ: " + clazz.getSimpleName());
		}
	}

}
