package team.dx.classroom.factory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 采用工程设计模式，解决接口延迟实现
 * 
 * 使用说明UserDAO uDao = DaoFactory.getInstance().createDao(UserDAO.class);
 * 
 * */
public class ObjectFactory {
	private static final ObjectFactory instance = new ObjectFactory();
	private static Properties config = new Properties();
	
	//启动时加载dao.properties文件进入内存,得到properties配置文件对象
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
	
	//--------实现创造对象方法--------------------
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
