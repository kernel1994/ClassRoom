package team.dx.classroom.service.impl;

import java.util.List;

import team.dx.classroom.dao.ExamsDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Select;
import team.dx.classroom.domain.ShortQuestion;
import team.dx.classroom.domain.TrueOrFalse;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.ExamsService;

public class ExamsServiceImpl implements ExamsService{
	
	private ExamsDAO eDAO = ObjectFactory.getInstance().createObject(
			ExamsDAO.class);
	

	@Override
	public void addExams(HomeWork homeWork) {
		List<Select> selects = homeWork.getSelects();
		List<ShortQuestion> shortQuestions = homeWork.getShortQuestions();
		List<TrueOrFalse> trueOrFalses = homeWork.getTrueOrFalses();
		
		//选择
		for (Select select : selects) {
			eDAO.addSelect(select);
		}
		
		//判断
		for (TrueOrFalse trueOrFalse : trueOrFalses) {
			eDAO.addTrueOrFalse(trueOrFalse);
		}
		
		//简答
		for (ShortQuestion shortQuestion : shortQuestions) {
			eDAO.addShortQuestion(shortQuestion);
		}
		
		
	}


	@Override
	public HomeWork createExams(String chapter, String degree,
			String knowledgepoint, String type, String examcount) {
		
		//对输入数据处理
		
		if ("0".equals(chapter)) {
			chapter = "%";
		}
		
		if ("0".equals(degree)) {
			degree = "%";
		}
		
		if ("".equals(knowledgepoint)) {
			knowledgepoint = "%";
		}
		
		if ("0".equals(type)) {
			type = "%";
		}
		
		if ("".equals(examcount)) {
			//默认每个类型（若没有选择类型）读取两个题目
			examcount = "2";
		}
		
		
		return eDAO.getExams(chapter,degree,knowledgepoint,type,examcount);
	}

}
