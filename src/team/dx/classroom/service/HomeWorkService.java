package team.dx.classroom.service;

import team.dx.classroom.domain.HomeWork;

public interface HomeWorkService {

    /**
     * ����taskId ��ȡ�ļ�����Homework
     * @param taskId ��ҵid
     * @return ����������Homework ����
     * */
    public HomeWork getHomeWork(String taskId);
}
