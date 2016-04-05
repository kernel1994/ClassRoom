package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.ExperimentDAO;
import team.dx.classroom.domain.Experiment;

public class ExperimentDAOImpl extends BasicDAO<Experiment> implements ExperimentDAO  {

	@Override
	public List<Experiment> getExperiments(String condition, Object... args) {
		
		return getForList(condition, args);
	}

	@Override
	public Experiment getExperiment(String condition, Object... args) {

		return get(condition, args);
	}

	@Override
	public void updateExperiment(Experiment experiment) {

		String sql = "UPDATE Experiment SET name = ?, description = ?, flag = ?, input = ?, output = ?,  resource_id = ? WHERE id = ?";
		
		update(sql, experiment.getName(), experiment.getDescription(), experiment.getFlag(), experiment.getInput(), experiment.getOutput(), experiment.getId());
	}


	@Override
	public void deleteExperiment(String id) {

		String sql = "DELETE FROM experiment WHERE id = ?";
		
		update(sql, id);
	}


	@Override
	public void addExperiment(Experiment experiment, String courseId) {

		String sql = "INSERT INTO experiment (id, name, description, flag, input, output, course_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		update(sql, experiment.getId(), experiment.getName(), experiment.getDescription(), experiment.getFlag(), experiment.getInput(), experiment.getOutput(), courseId);
	}

	@Override
	public Integer getScore(String sql, Object... args) {
	
		return getTheValue(sql, args);
	}
}
