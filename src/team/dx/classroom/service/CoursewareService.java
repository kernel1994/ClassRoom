package team.dx.classroom.service;

import team.dx.classroom.domain.Courseware;

import java.util.List;

public interface CoursewareService {

    /**
     * ���ָ���γ̺ŵ�������ҵ�б�
     * @param courseId ָ���Ŀγ�id
     * @return �ÿγ̺ŵ�������ҵList ����
     * */
    public List<Courseware> getCoursewares(String courseId);
}
