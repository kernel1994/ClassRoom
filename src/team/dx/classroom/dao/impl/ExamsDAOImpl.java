package team.dx.classroom.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import team.dx.classroom.dao.ExamsDAO;
import team.dx.classroom.domain.*;
import team.dx.classroom.exception.DaoException;
import team.dx.classroom.utils.JDBCUtils2;

import java.sql.SQLException;
import java.util.*;

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
			Object[] args = null;
			String sql = null;
			Object[] count = null;

			//选择
			if (type.equals("%")) {
				sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ?";
				args = new Object[]{chapter,degree,knowledgepoint,1};
				count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, new ArrayHandler(), args);
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
					sql =  "select id, chapter, degree, knowledgepoint, score, types, title, answer, description, a, b,  c,  d from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ? limit ?, 1";
					args = new Object[]{chapter,degree,knowledgepoint,1,object};
					Select select = (Select) queryRunner.query(JDBCUtils2.getConnection(), sql, new BeanHandler(Select.class), args);
					selects.add(select);
				}
				set.clear();


				//判断

				sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ?";
				args = new Object[]{chapter,degree,knowledgepoint,2};
				count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, new ArrayHandler(), args);
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
					sql =  "select id, chapter, degree, knowledgepoint, score, types, title, answer, description from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ? limit ?, 1";
					args = new Object[]{chapter,degree,knowledgepoint,2, object};
					TrueOrFalse trueOrFalse = (TrueOrFalse) queryRunner.query(JDBCUtils2.getConnection(), sql, new BeanHandler(TrueOrFalse.class), args);
					trueOrFalses.add(trueOrFalse);
				}
				set.clear();

				//问答

				sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ?";
				args = new Object[]{chapter,degree,knowledgepoint,3};
				count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, new ArrayHandler(), args);
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
					sql =  "select id, chapter, degree, knowledgepoint, score, types, title, description from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ? limit ?, 1";
					args = new Object[]{chapter,degree,knowledgepoint,3,object};
					ShortQuestion shortQuestion = (ShortQuestion) queryRunner.query(JDBCUtils2.getConnection(), sql, new BeanHandler(ShortQuestion.class), args);
					shortQuestions.add(shortQuestion);
				}
				set.clear();
			} else {
				if (type.equals("1")) {
					sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ?";
					args = new Object[]{chapter,degree,knowledgepoint,type};
					count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, new ArrayHandler(), args);
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
						sql =  "select id, chapter, degree, knowledgepoint, score, types, title, answer, description, a, b,  c,  d from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ? limit ?, 1";
						args = new Object[]{chapter,degree,knowledgepoint,type,object};
						Select select = (Select) queryRunner.query(JDBCUtils2.getConnection(), sql, new BeanHandler(Select.class), args);
						selects.add(select);
					}
				}
				if (type.equals("2")) {
					//判断

					sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ?";
					args = new Object[]{chapter,degree,knowledgepoint,type};
					count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, new ArrayHandler(), args);
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
						sql =  "select id, chapter, degree, knowledgepoint, score, types, title, answer, description from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ? limit ?, 1";
						args = new Object[]{chapter,degree,knowledgepoint,type, object};
						TrueOrFalse trueOrFalse = (TrueOrFalse) queryRunner.query(JDBCUtils2.getConnection(), sql, new BeanHandler(TrueOrFalse.class), args);
						trueOrFalses.add(trueOrFalse);
					}
				}
				if (type.equals("3")) {
					sql = "select count(*) from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ?";
					args = new Object[]{chapter,degree,knowledgepoint,type};
					count = (Object[]) queryRunner.query(JDBCUtils2.getConnection(), sql, new ArrayHandler(), args);
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
						sql =  "select id, chapter, degree, knowledgepoint, score, types, title, description from exams where chapter like ? and degree like ? and knowledgepoint like ? and types like ? limit ?, 1";
						args = new Object[]{chapter,degree,knowledgepoint,type,object};
						ShortQuestion shortQuestion = (ShortQuestion) queryRunner.query(JDBCUtils2.getConnection(), sql, new BeanHandler(ShortQuestion.class), args);
						shortQuestions.add(shortQuestion);
					}
				}
			}



			return new HomeWork(selects, trueOrFalses, shortQuestions);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("出现异常为: ExamsDAOImpl");
		}
	}

}

