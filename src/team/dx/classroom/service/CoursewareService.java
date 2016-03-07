package team.dx.classroom.service;

import team.dx.classroom.domain.Courseware;

import java.util.List;

public interface CoursewareService {

    /**
     * 根据课件ID 获取对象
     * */
    public Courseware getCourseware(String coursewareID);

     /**
     * 获得指定课程号的所有课件列表
     * @param courseId 指定的课程id
     * @return 该课程号的所有作业List 对象
     * */
    public List<Courseware> getCoursewares(String courseId);
    

    /**
     * 添加课件的信息到数据库
     * @param courseware 封装课程的信息
     * */
    void addCourseware(Courseware courseware, String courseId);


    /**
     * 获取一次课件的路径
     * @taskId 课件的保存路径id
     * @pathName 作课件的保存路径
     * */
	public String getCoursewarePath(String coursewareId);


	/**
     * 删除一次课件
     * @taskId 课件的id
     * */
	public void deleteCourseware(String coursewareId);
}
