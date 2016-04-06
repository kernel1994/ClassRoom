package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.domain.Experiment;

public interface ExperimentService {

	//添加课程
	void addExperiment(Experiment experiment, String courseId);

	//列出所有课程
	List<Experiment> getExperiments(String courseId);

	List<Experiment> getCourseExperiments(String studentId);

	Experiment getExperiment(String experimentId);

	//判断用户是否已经上传过实验
	boolean isUplod(String id, String userId);

	void updateExperimentScore(String experimentId, String userId, int score);

	void insertExperimentScore(String experimentId, String userId, int score);
	
	//得到用户分数
	Integer getExperimentScore(String id, String userId);

}