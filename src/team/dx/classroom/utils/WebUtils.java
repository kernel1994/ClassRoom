package team.dx.classroom.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.Select;
import team.dx.classroom.domain.ShortQuestion;
import team.dx.classroom.domain.Task;
import team.dx.classroom.domain.TrueOrFalse;
import team.dx.classroom.domain.User;

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
		List<Select> selects = new ArrayList<Select>();
		if (titles == null) {
			return selects;
		}
		int len = titles.length;
		for (int i = 0; i < len; i++) {
			/*Select select = new Select(titles[i], answersA[i], answersB[i],
					answersC[i], answersD[i], answers[i], descriptions[i]);*/
			Select select = new Select(titles[i], answersA[i], answersB[i], answersC[i],answersD[i],answers[i],descriptions[i]);
			selects.add(select);
		}
		return selects;

	}

	public static List<TrueOrFalse> conver2TrueOrFalse(String[] ttitles,
			String[] tanswers, String[] tdescriptions) {
		List<TrueOrFalse> trueOrFalses = new ArrayList<TrueOrFalse>();
		if (ttitles == null) {
			return null;
		}
		int len = ttitles.length;
		
		for (int i = 0; i < len; i++) {
			TrueOrFalse trueOrFalse = new TrueOrFalse(ttitles[i], tanswers[i],
					tdescriptions[i]);
			trueOrFalses.add(trueOrFalse);
		}
		return trueOrFalses;
	}

	public static List<ShortQuestion> conver2ShortQuestion(String[] qtitles,
			String[] qdescriptions) {
		List<ShortQuestion> shortQuestions = new ArrayList<ShortQuestion>();
		if (qtitles == null) {
			return shortQuestions;
		}
		int len = qtitles.length;
		
		for (int i = 0; i < len; i++) {
			ShortQuestion shortQuestion = new ShortQuestion(qtitles[i],
					qdescriptions[i]);
			shortQuestions.add(shortQuestion);
		}
		return shortQuestions;
	}

	public static Resource conver2Resource(Task task, User uploader, String path) {

		Resource resource = new Resource();
		resource.setId(getRandomUUID());
		resource.setName(task.getName() + "_"+ uploader.getId()+".xml");
		resource.setDescription(task.getDescription());
		resource.setUploadtime(new Date());
		
		String uri = path + File.separator + resource.getName();
		resource.setUri(uri);
		resource.setUploader(uploader);
		return resource;
	}

	// 封装在线编辑作业信息
	public static HomeWork request2HomeWork(HttpServletRequest request) {

		// 真实的作业数据
		HomeWork homeWork = new HomeWork();

		// 选择题
		String[] stitles = request.getParameterValues("stitle");
		String[] sanswersA = request.getParameterValues("sA");
		String[] sanswersB = request.getParameterValues("sB");
		String[] sanswersC = request.getParameterValues("sC");
		String[] sanswersD = request.getParameterValues("sD");
		String[] sdescriptions = request.getParameterValues("sdescription");
		String[] sanswers = request.getParameterValues("sanswer");
		List<Select> selects = conver2Selects(stitles, sanswersA,
				sanswersB, sanswersC, sanswersD, sdescriptions, sanswers);
		homeWork.setSelects(selects);

		// 判断题
		String[] ttitles = request.getParameterValues("ttitle");
		String[] tanswers = request.getParameterValues("tanswer");
		String[] tdescriptions = request.getParameterValues("tdescription");
		List<TrueOrFalse> trueOrFalses = conver2TrueOrFalse(ttitles,
				tanswers, tdescriptions);
		homeWork.setTrueOrFalses(trueOrFalses);

		// 简答题
		String[] qtitles = request.getParameterValues("qtitle");
		String[] qdescriptions = request.getParameterValues("qdescription");
		List<ShortQuestion> shortQuestions = conver2ShortQuestion(
				qtitles, qdescriptions);
		homeWork.setShortQuestions(shortQuestions);

		return homeWork;
	}

}