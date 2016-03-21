package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Experiment;

public interface ExperimentDAO {

	List<Experiment> getExperiments(String condition, Object... args);

	Experiment getExperiment(String condition, Object... args);

	void updateExperiment(Experiment experiment);

	void deleteExperiment(String id);

	void addExperiment(Experiment experiment, String courseId);

}