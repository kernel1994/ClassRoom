package team.dx.classroom.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils {

	public static <T> T request2Bean(Map<?, ?> parameterMap, Class<T> clazz) {

		try {
			T bean = clazz.newInstance();

			// Date������8�ֻ������ͣ���Ҫ�Լ�дת����
			ConvertUtils.register(new Converter() {
				@Override
				public Object convert(Class type, Object value) {
					if (value == null) {
						return null;
					}
					String str = (String) value;
					if (str.trim().equals("")) {
						return null;
					}
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						return sf.parse(str);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}, Date.class);

			// ��parameterMap������--->���Ƶ�bean��
			BeanUtils.populate(bean, parameterMap);

			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// �����ύ������������������������װ��ͬuser����ͬ�ı���
	public static Date birthdayUtils(Map<?, ?> parameterMap) {
		String year = ((String[]) parameterMap.get("year"))[0];
		String month = ((String[]) parameterMap.get("month"))[0];
		String day = ((String[]) parameterMap.get("day"))[0];
		
		if (year == null || month == null || month == null) {
			return null;
		}
		
		String birthday = year + "-" + month + "-" + day;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sf.parse(birthday);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}

}