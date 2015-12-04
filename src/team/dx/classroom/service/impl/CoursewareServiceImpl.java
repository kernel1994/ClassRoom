package team.dx.classroom.service.impl;

import team.dx.classroom.dao.CoursewareDAO;
import team.dx.classroom.dao.ResourceDAO;
import team.dx.classroom.domain.Courseware;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.CoursewareService;

import java.util.List;

public class CoursewareServiceImpl implements CoursewareService {

    private CoursewareDAO cwDAO = ObjectFactory.getInstance().createObject(CoursewareDAO.class);
    private ResourceDAO rDAO = ObjectFactory.getInstance().createObject(ResourceDAO.class);

    @Override
    public List<Courseware> getCoursewares(String courseId) {

        String sqlCw = "select * from courseware where course_id = ?";
        List<Courseware> coursewares = cwDAO.getCoursewares(sqlCw, courseId);

        String sqlR = "select * from resource where id = ?";
        for (Courseware courseware : coursewares) {
            Resource resource = rDAO.getResource(sqlR, courseware.getResource_id());
            courseware.setResource(resource);
        }

        return coursewares;
    }

	@Override
	public void addCourseware(Courseware courseware, String courseId) {
		rDAO.addResource(courseware.getResource());
		cwDAO.addCourseware(courseware, courseId);
	}

	@Override
	public String getCoursewarePath(String coursewareId) {
		
		Courseware courseware = cwDAO.getCourseware("select * from courseware where id = ?", coursewareId);
		Resource resource = rDAO.getResource("select * from resource where id = ?", courseware.getResource_id());
		return resource.getUri();
	}

	@Override
	public void deleteCourseware(String coursewareId) {
		Courseware courseware = cwDAO.getCourseware("select * from courseware where id = ?", coursewareId);
		//删除courseware中记录
		cwDAO.deleteCourseware(coursewareId);
		//删除资源resource中的记录
		rDAO.deleteResource(courseware.getResource_id());
	}
}
