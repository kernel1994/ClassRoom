package team.dx.classroom.factory;

import java.io.InputStream;
import java.util.Properties;

/**
 * ���ù������ģʽ������ӿ��ӳ�ʵ��
 * 
 * ʹ��˵��UserDAO uDao = DaoFactory.getInstance().createDao(UserDAO.class);
 * 
 * */
public class ObjectFactory {
	private static final ObjectFactory instance = new ObjectFactory();
	private static Properties config = new Properties();
	
	//����ʱ����dao.properties�ļ������ڴ�,�õ�properties�����ļ�����
	static {
		try {
			InputStream in = ObjectFactory.class.getClassLoader().getResourceAsStream("dao.properties");
			config.load(in);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static ObjectFactory getInstance() {
		return instance;
	}
	
	//--------ʵ�ִ�����󷽷�--------------------
	public <T> T createObject(Class<T> interfaceClass) {

		try {
			String key = interfaceClass.getSimpleName();
			String className = config.getProperty(key);
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
