package team.dx.classroom.dao.impl;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import team.dx.classroom.dao.HomeWorkDAO;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Select;
import team.dx.classroom.domain.ShortQuestion;
import team.dx.classroom.domain.TrueOrFalse;
import team.dx.classroom.utils.XmlUtils;

public class HomeWorkDAOImpl implements HomeWorkDAO {

	@Override
	public void add(HomeWork homeWork, String path, String standardPath) {
		try {
			Document document = XmlUtils.parse(standardPath);
			Element root = document.getRootElement();
			
			List<Select> selects = homeWork.getSelects();
			List<TrueOrFalse> trueOrFalses = homeWork.getTrueOrFalses();
			List<ShortQuestion> shortQuestions = homeWork.getShortQuestions();
			
			for (Select select : selects) {
				Element selectTag = root.addElement( "select" );
				selectTag.addAttribute( "answer", select.getAnswer() );
				selectTag.addElement("title").setText(select.getTitle());
				selectTag.addElement("A").setText(select.getA());
				selectTag.addElement("B").setText(select.getB());
				selectTag.addElement("C").setText(select.getC());
				selectTag.addElement("D").setText(select.getD());
				selectTag.addElement("description").setText(select.getDescription());
			}
			
			for (TrueOrFalse trueOrFalse : trueOrFalses) {
				Element trueOrFalseTag = root.addElement( "trueorfalse" );
				trueOrFalseTag.addAttribute( "answer", trueOrFalse.getAnswer());
				trueOrFalseTag.addElement("title").setText(trueOrFalse.getTitle());
				trueOrFalseTag.addElement("description").setText(trueOrFalse.getDescription());
		
			}
			
			for (ShortQuestion shortQuestion : shortQuestions) {
				Element shortQuestionTag = root.addElement( "shortquestion" );
				shortQuestionTag.addElement("title").setText(shortQuestion.getTitle());
				shortQuestionTag.addElement("description").setText(shortQuestion.getDescription());
			}
			
			XmlUtils.write(document,path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
