package team.dx.classroom.service;

/**
 * Created by kernel on 2016/8/12.
 */
public interface OnlineTestService {
    /**
     * 创建标准的测试，假定是两个选择题，两个判断题，一个问答题，一易一难
     */
    String createStanderOnlineTest(String userID, String homeworkDir, String templateXMLPath);

    /**
     * 创建自定义的测试，自定义的题目规则
     * @param uerID
     * @param homeworkDir 存放作业文件的实际物理路径，只能由 servlet 传递过来
     * @param templateXMLPath 模板作业文件的实际物理路径，只能由 servlet 传递过来
     * @param chapter
     * @param degree
     * @param knowledgepoint
     * @param type
     * @param examcount
     * @return 生成的 Task ID
     */
    String createCustomOnlineTest(String uerID, String homeworkDir, String templateXMLPath, String chapter, String degree, String knowledgepoint, String type, String examcount);
}
