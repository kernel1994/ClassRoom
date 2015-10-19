package team.dx.classroom.service;

import team.dx.classroom.domain.Courseware;

import java.util.List;

public interface CoursewareService {

    /**
     * 获得指定课程号的所有作业列表
     * @param courseId 指定的课程id
     * @return 该课程号的所有作业List 对象
     * */
    public List<Courseware> getCoursewares(String courseId);
}
