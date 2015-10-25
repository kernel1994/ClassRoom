package team.dx.classroom.service;

import team.dx.classroom.domain.Courseware;

import java.util.List;

public interface CoursewareService {

    /**
     * ���ָ���γ̺ŵ����пμ��б�
     * @param courseId ָ���Ŀγ�id
     * @return �ÿγ̺ŵ�������ҵList ����
     * */
    public List<Courseware> getCoursewares(String courseId);
    

    /**
     * ��ӿμ�����Ϣ�����ݿ�
     * @param courseware ��װ�γ̵���Ϣ
     * */
    void addCourseware(Courseware courseware, String courseId);
}
