package team.dx.classroom.service.impl;

import java.util.List;

import team.dx.classroom.dao.ExperimentDAO;
import team.dx.classroom.dao.ThirdPartyCommonDAO;
import team.dx.classroom.domain.Experiment;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.ExperimentService;

public class ExperimentServiceImpl implements ExperimentService {

	private ExperimentDAO eDAO = ObjectFactory.getInstance().createObject(
			ExperimentDAO.class);
	private ThirdPartyCommonDAO tpDAO = ObjectFactory.getInstance().createObject(
			ThirdPartyCommonDAO.class);
	
	@Override
	public void addExperiment(Experiment experiment, String courseId) {
		eDAO.addExperiment(experiment, courseId);
	}

	@Override
	public List<Experiment> getExperiments(String courseId) {
		String sql = "select * from experiment where course_id = ?";
		return eDAO.getExperiments(sql, courseId);
	}

	@Override
	public List<Experiment> getCourseExperiments(String studentId) {
	
		return null;
	}

	@Override
	public Experiment getExperiment(String experimentId) {

		String condition = "select * from experiment where id = ?";
		
		return eDAO.getExperiment(condition, experimentId);
	}

	@Override
	public boolean isUplod(String id, String userId) {
		Integer score = eDAO.getScore("select score from user_experiment WHERE user_id = ? AND experiment_id = ?", userId, id);
		
		if (score != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	

	@Override
	public void updateExperimentScore(String experimentId, String userId,
			int score) {
		tpDAO.updateUserExperiment("update", score, userId, experimentId);
	}

	@Override
	public void insertExperimentScore(String experimentId, String userId,
			int score) {
		tpDAO.updateUserExperiment("insert", userId, experimentId, score);
	}

	@Override
	public Integer getExperimentScore(String id, String userId) {
		Integer score = eDAO.getScore("select score from user_experiment WHERE user_id = ? AND experiment_id = ?", userId, id);
		
		return score;
	}

}
