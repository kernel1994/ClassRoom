package team.dx.classroom.service;

import team.dx.classroom.domain.HomeWork;

import java.util.HashMap;
import java.util.Map;

public interface HomeWorkService {

    /**
     * ����taskId ��ȡ�ļ�����Homework
     * @param taskId ��ҵid
     * @return ����������Homework ����
     * */
    public HomeWork getHomeWork(String taskId);

    /**
     * ���ѧ�������⣬����������������Map���󡣲��ƻ���д�����ݿ⣬��ѧ���Ĵ𰸴洢
     * @param taskId ��ҵid����ȡ��׼��
     * @param stuAnswer ѧ���Ĵ�Map ����
     * @return ѧ���������
     * */
    public HashMap<String, String> checkHomework(String taskId, Map stuAnswer);
}
