package team.dx.classroom.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import team.dx.classroom.dao.ExamsDAO;
import team.dx.classroom.domain.HomeWork;
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



	@Override
	public HomeWork getExams(String chapter, String degree,
			String knowledgepoint, String type, String examcount) {
		
		 List<Select> selects = new ArrayList<Select>();
		 List<TrueOrFalse> trueOrFalses = new ArrayList<TrueOrFalse>();
		 List<ShortQuestion> shortQuestions = new ArrayList<ShortQuestion>();
		
		try {
			
			//1.根据输入条件预判断返回题目数目
			
			//选择
			String tempTypeString = type;
			if (type.equals("%")) {
				tempTypeString = "1";
			}
			String sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types = ?";
			Object[] args = {chapter,degree,knowledgepoint,tempTypeString};
			Object[] count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, args, new ArrayHandler());
			int totalrecord = Integer.parseInt(count[0] + "");
			
			int selectNum = Integer.parseInt(examcount);;
			if (totalrecord < selectNum) {
				//throw new RuntimeException("请先添加题库_选择");
				selectNum =  totalrecord;
			}
			//随机读取数据库
			Random r = new Random();
			Set set = new HashSet();
			while (set.size() != selectNum){
				int value = (int) (r.nextDouble() * selectNum);
				set.add(value);
			}

			
			for (Object object : set) {
				sql =  "select id, chapter, degree, knowledgepoint, score, types, title, answer, description, a, b,  c,  d from exams where chapter like ? and degree like ? and knowledgepoint like ? and types = ? limit ?, 1";
				args = new Object[]{chapter,degree,knowledgepoint,tempTypeString,object};
				Select select = queryRunner.query(JDBCUtils2.getConnection(), sql, args, new BeanHandler(Select.class));
				selects.add(select);
			}
			set.clear();
			
			
			//判断
			tempTypeString = type;
			if (type.equals("%")) {
				tempTypeString = "2";
			}
			
			sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types = ?";
			args = new Object[]{chapter,degree,knowledgepoint,tempTypeString};
			count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, args, new ArrayHandler());
			totalrecord = Integer.parseInt(count[0] + "");
			
			selectNum = Integer.parseInt(examcount);;
			if (totalrecord < selectNum) {
				//throw new RuntimeException("请先添加题库_选择");
				selectNum =  totalrecord;
			}
			//随机读取数据库
			r = new Random();
			set = new HashSet();
			while (set.size() != selectNum){
				int value = (int) (r.nextDouble() * selectNum);
				set.add(value);
			}

			
			for (Object object : set) {
				sql =  "select id, chapter, degree, knowledgepoint, score, types, title, answer, description from exams where chapter like ? and degree like ? and knowledgepoint like ? and types = ? limit ?, 1";
				args = new Object[]{chapter,degree,knowledgepoint,tempTypeString, object};
				TrueOrFalse trueOrFalse = queryRunner.query(JDBCUtils2.getConnection(), sql, args, new BeanHandler(TrueOrFalse.class));
				trueOrFalses.add(trueOrFalse);
			}
			set.clear();
			
			//问答
			tempTypeString = type;
			if (type.equals("%")) {
				tempTypeString = "3";
			}
			
			sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types = ?";
			args = new Object[]{chapter,degree,knowledgepoint,tempTypeString};
			count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, args, new ArrayHandler());
			totalrecord = Integer.parseInt(count[0] + "");
			
			selectNum = Integer.parseInt(examcount);;
			if (totalrecord < selectNum) {
				//throw new RuntimeException("请先添加题库_选择");
				selectNum =  totalrecord;
			}
			//随机读取数据库
			r = new Random();
			set = new HashSet();
			while (set.size() != selectNum){
				int value = (int) (r.nextDouble() * selectNum);
				set.add(value);
			}

			
			for (Object object : set) {
				sql =  "select id, chapter, degree, knowledgepoint, score, types, title, description from exams where chapter like ? and degree like ? and knowledgepoint like ? and types = ? limit ?, 1";
				args = new Object[]{chapter,degree,knowledgepoint,tempTypeString,object};
				ShortQuestion shortQuestion = queryRunner.query(JDBCUtils2.getConnection(), sql, args, new BeanHandler(ShortQuestion.class));
				shortQuestions.add(shortQuestion);
			}
			set.clear();
			
			return new HomeWork(selects, trueOrFalses, shortQuestions);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("出现异常为: ExamsDAOImpl");
		}
	}

}

