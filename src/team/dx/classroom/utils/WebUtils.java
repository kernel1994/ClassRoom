package team.dx.classroom.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import team.dx.classroom.domain.Select;

@SuppressWarnings("all")
public class WebUtils {

	public static <T> T request2Bean(Map<?, ?> parameterMap, Class<T> clazz) {

		try {
			T bean = clazz.newInstance();

			// Date不属于8种基本类型，需要自己写转换器
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

			// 讲parameterMap中数据--->复制到bean中
			BeanUtils.populate(bean, parameterMap);

			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// 处理提交数据中年月日三个变量，组装成同user域相同的变量
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

	public static List<Select> conver2Selects(String[] titles,
			String[] answersA, String[] answersB, String[] answersC,
			String[] answersD, String[] descriptions, String[] answers) {
		int len = titles.length;
		List<Select> selects = new ArrayList<Select>();
		for (int i = 0; i < len; i++) {
			Select select = new Select(titles[i], answersA[i], answersB[i],
					answersC[i], answersD[i], answers[i], descriptions[i+1]);
			selects.add(select);
		}
		return selects;

	}
}