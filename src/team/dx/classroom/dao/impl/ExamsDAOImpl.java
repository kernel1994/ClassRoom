package team.dx.classroom.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import team.dx.classroom.dao.ExamsDAO;
import team.dx.classroom.domain.Select;
import team.dx.classroom.domain.ShortQuestion;
import team.dx.classroom.domain.Topic;
import team.dx.classroom.domain.TrueOrFalse;
import team.dx.classroom.exception.DaoException;
import team.dx.classroom.utils.JDBCUtils2;

//
public class ExamsDAOImpl implements ExamsDAO {
	
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public void addSelect(Select s) {
		String sql = "INSERT INTO exams (id, chapter, degree, knowledgepoint, "
				+ "score, title, types, answer, a, b, c, d,"
				+ " description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = {s.getId(), s.getChapter(), s.getDegree(), s.getKnowledgepoint(),
				s.getScore(), s.getTitle(), s.getTypes(), s.getAnswer(), s.getA(), s.getB(),
				s.getC(), s.getD(), s.getDescription()};
		add(sql, args); 
	}

	

	@Override
	public void addTrueOrFalse(TrueOrFalse t) {
		String sql = "INSERT INTO exams (id, chapter, degree, knowledgepoint, "
				+ "score, title, types, answer, a, b, c, d,"
				+ " description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = {t.getId(), t.getChapter(), t.getDegree(), t.getKnowledgepoint(),
				t.getScore(), t.getTitle(), t.getTypes(), t.getAnswer(), null, null,
				null, null, t.getDescription()};
		add(sql, args); 
	}

	@Override
	public void addShortQuestion(ShortQuestion s) {
		String sql = "INSERT INTO exams (id, chapter, degree, knowledgepoint, "
				+ "score, title, types, answer, a, b, c, d,"
				+ " description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = {s.getId(), s.getChapter(), s.getDegree(), s.getKnowledgepoint(),
				s.getScore(), s.getTitle(), s.getTypes(), null, null, null,
				null, null, s.getDescription()};
		add(sql, args); 
	}
	
	@Override
	public void addExam(Topic t) {
		
	}
	
	
	private void add(String sql, Object[] args) {
		try {
			
			queryRunner.update(JDBCUtils2.getConnection(), sql, args);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("出现异常为: ExamsDAOImpl");
		}
	}

}

