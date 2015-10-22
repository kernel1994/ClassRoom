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
import team.dx.classroom.domain.ShortQuestion;
import team.dx.classroom.domain.TrueOrFalse;

@SuppressWarnings("all")
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

	public static List<Select> conver2Selects(String[] titles,
			String[] answersA, String[] answersB, String[] answersC,
			String[] answersD, String[] descriptions, String[] answers) {
		int len = titles.length;
		List<Select> selects = new ArrayList<Select>();
		for (int i = 0; i < len; i++) {
			Select select = new Select(titles[i], answersA[i], answersB[i],
					answersC[i], answersD[i], answers[i], descriptions[i]);
			selects.add(select);
		}
		return selects;

	}

	public static List<TrueOrFalse> conver2TrueOrFalse(String[] ttitles,
			String[] tanswers, String[] tdescriptions) {
		int len = ttitles.length;
		List<TrueOrFalse> trueOrFalses = new ArrayList<TrueOrFalse>();
		for (int i = 0; i < len; i++) {
			TrueOrFalse trueOrFalse = new TrueOrFalse(ttitles[i], tanswers[i], tdescriptions[i]);
			trueOrFalses.add(trueOrFalse);
		}
		return trueOrFalses;
	}

	public static List<ShortQuestion> conver2ShortQuestion(String[] qtitles,
			String[] qdescriptions) {
		int len = qtitles.length;
		List<ShortQuestion> shortQuestions = new ArrayList<ShortQuestion>();
		for (int i = 0; i < len; i++) {
			ShortQuestion shortQuestion = new ShortQuestion(qtitles[i], qdescriptions[i]);
			shortQuestions.add(shortQuestion);
		}
		return shortQuestions;
	}
}