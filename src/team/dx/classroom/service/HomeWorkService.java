package team.dx.classroom.service;

import team.dx.classroom.domain.HomeWork;

public interface HomeWorkService {

    /**
     * 根据taskId 读取文件生成Homework
     * @param taskId 作业id
     * @return 解析出来的Homework 对象
     * */
    public HomeWork getHomeWork(String taskId);
}
