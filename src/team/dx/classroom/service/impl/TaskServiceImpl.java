package team.dx.classroom.service.impl;

import team.dx.classroom.dao.ResourceDAO;
import team.dx.classroom.dao.TaskDAO;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.Task;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskDAO tDAO = ObjectFactory.getInstance().createObject(TaskDAO.class);
    private ResourceDAO rDAO = ObjectFactory.getInstance().createObject(ResourceDAO.class);

    @Override
    public List<Task> getCourseTasks(String courseId) {

        String sqlTask = "select * from task where course_id = ?";
        List<Task> tasks = tDAO.getTasks(sqlTask, courseId);

        String sqlR = "select * from resource where id = ?";
        for (Task task : tasks) {
            Resource resource = rDAO.getResource(sqlR, task.getResource_id());
            task.setResource(resource);
        }

        return tasks;
    }
}
