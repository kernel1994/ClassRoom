package team.dx.classroom.service;

import team.dx.classroom.domain.Task;

import java.util.List;

public interface TaskService {

    /**
     * ���ָ���γ̺ŵ����пμ��б�
     * @param courseId ָ���Ŀγ�id
     * @return �ÿγ̺ŵ����пμ�List ����
     * */
    public List<Task> getCourseTasks(String courseId);
}
