package team.dx.classroom.dao;

import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Select;
import team.dx.classroom.domain.ShortQuestion;
import team.dx.classroom.domain.Topic;
import team.dx.classroom.domain.TrueOrFalse;

public interface ExamsDAO {
	
	public void addSelect(Select s);
	
	public void addTrueOrFalse(TrueOrFalse t);
	
	public void addShortQuestion(ShortQuestion s);
	
	public void addExam(Topic t);

	public HomeWork getExams(String chapter, String degree,
			String knowledgepoint, String type, String examcount);
	
}
