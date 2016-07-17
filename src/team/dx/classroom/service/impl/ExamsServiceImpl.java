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

}
