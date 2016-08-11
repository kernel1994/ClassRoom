package team.dx.classroom.service;

import team.dx.classroom.domain.HomeWork;

/**
 * Created by kernel on 2015/12/2 14:25.
 */
public interface ExamsService {;

    /** 添加题库 */
    void addExams(HomeWork homeWork);

    /** 生成题库 */
	HomeWork createExams(String chapter, String degree, String knowledgepoint,
			String type, String examcount);
    
    
}
