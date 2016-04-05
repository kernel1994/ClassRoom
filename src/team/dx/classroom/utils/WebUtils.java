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

import team.dx.classroom.domain.Course;
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

			// 将parameterMap中数据--->复制到bean中
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


	public static List<ShortQuestion> conver2ShortQuestion(String[] qtitles,
			String[] qdescriptions) {
		List<ShortQuestion> shortQuestions = new ArrayList<ShortQuestion>();
		if (qtitles == null) {
			return shortQuestions;
		}
		int len = qtitles.length;
		
		for (int i = 0; i < len; i++) {
			ShortQuestion shortQuestion = new ShortQuestion("shortquestion" + i, qtitles[i],
					qdescriptions[i]);
			shortQuestions.add(shortQuestion);
		}
		return shortQuestions;
	}

	public static Resource conver2Resource(Task task, User uploader, Course course, String path) {

		Resource resource = new Resource();
		resource.setId(getRandomUUID());
		resource.setName(course.getName() + "_" + task.getName() + "_"+ uploader.getId()+".xml");
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
		String[] selectIds = request.getParameterValues("select_id");
		List<Select> selects = new ArrayList<Select>();	
		if (selectIds != null) {
			for (String id : selectIds) {
				String stitle = request.getParameter("stitle" + id);
				String sanswerA = request.getParameter("sA" + id);
				String sanswerB = request.getParameter("sB" + id);
				String sanswerC = request.getParameter("sC" + id);
				String sanswerD = request.getParameter("sD" + id);
				String sdescription = request.getParameter("sdescription" + id);
				String sanswer = request.getParameter("sanswer" + id);
				Select select = new Select("select", stitle, sanswerA, sanswerB,
						sanswerC, sanswerD, sanswer, sdescription);
				selects.add(select);
			}
		}
		
		homeWork.setSelects(selects);

		// 判断题
		String[] trueorfalseId = request.getParameterValues("trueorfalse_id");
		List<TrueOrFalse> trueOrFalses = new ArrayList<TrueOrFalse>();
		if (trueorfalseId != null) {
			for (String id : trueorfalseId) {
				String ttitle = request.getParameter("ttitle" + id);
				String tanswer = request.getParameter("tanswer" + id);
				String tdescription = request.getParameter("tdescription" + id);
				TrueOrFalse trueOrFalse = new TrueOrFalse("trueorfalse", ttitle, tanswer,
						tdescription);
				trueOrFalses.add(trueOrFalse);
			}
		}
		
		
		homeWork.setTrueOrFalses(trueOrFalses);

		// 简答题
		String[] qtitles = request.getParameterValues("qtitle");
		String[] qdescriptions = request.getParameterValues("qdescription");
		List<ShortQuestion> shortQuestions = conver2ShortQuestion(
				qtitles, qdescriptions);
		homeWork.setShortQuestions(shortQuestions);

		return homeWork;
	}

	public static void deleteFile(String pathname) {
		File file = new File(pathname);
		
		//文件不存在直接返回
		if (!file.exists()) {
			return;
		}
		
		//文件夹
		if (file.isDirectory()) {
			String[] filenames = file.list();
			File tempFile = null;
			for (int i = 0, length = filenames.length; i < length; i++) {
				//删除所有
			}
		} else { //删除单个文件
			file.delete();
		}
		
	}

}