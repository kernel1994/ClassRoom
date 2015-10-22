package team.dx.classroom.service;

import java.util.List;

import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Task;

public interface TaskService {

    /**
     * ���ָ���γ̺ŵ����пμ��б�
     * @param courseId ָ���Ŀγ�id
     * @return �ÿγ̺ŵ����пμ�List ����
     * */
    public List<Task> getCourseTasks(String courseId);
    
    void addTask(Task task, String courseId);
    
    void addHomeWork(HomeWork homeWork, String path, String standardPath);
}
