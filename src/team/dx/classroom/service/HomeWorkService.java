package team.dx.classroom.service;

import team.dx.classroom.domain.HomeWork;

import java.util.HashMap;
import java.util.Map;

public interface HomeWorkService {

    /**
     * 根据taskId 读取文件生成Homework
     * @param taskId 作业id
     * @return 解析出来的Homework 对象
     * */
    public HomeWork getHomeWork(String taskId);

    /**
     * 检查学生做的题，并返回其做错的题的Map对象。并计积分写入数据库，把学生的答案存储
     * @param taskId 作业id，获取标准答案
     * @param stuAnswer 学生的答案Map 对象
     * @return 学生做错的题
     * */
    public HashMap<String, String> checkHomework(String taskId, Map stuAnswer);

    /**
     * 获得某次作业的及其答案和学生的答案
     * */
    HomeWork getStudentHomeWork(String taskId, String studentId);

}
