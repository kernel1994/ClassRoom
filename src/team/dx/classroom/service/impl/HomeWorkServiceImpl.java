package team.dx.classroom.service.impl;

import team.dx.classroom.dao.HomeWorkDAO;
import team.dx.classroom.dao.ResourceDAO;
import team.dx.classroom.dao.TaskDAO;
import team.dx.classroom.domain.*;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.HomeWorkService;

import java.util.HashMap;
import java.util.Map;

public class HomeWorkServiceImpl implements HomeWorkService {

    private TaskDAO tDAO = ObjectFactory.getInstance().createObject(TaskDAO.class);
    private ResourceDAO rDAO = ObjectFactory.getInstance().createObject(ResourceDAO.class);
    private HomeWorkDAO hDAO = ObjectFactory.getInstance().createObject(HomeWorkDAO.class);

    @Override
    public HomeWork getHomeWork(String taskId) {

        String sqlT = "select * from task where id = ?";
        Task task = tDAO.getTask(sqlT, taskId);

        String sqlR = "select * from resource where id = ?";
        Resource resource = rDAO.getResource(sqlR, task.getResource_id());

        HomeWork homework = hDAO.get(resource.getUri());

        return homework;
    }

    @Override
    public HashMap<String, String> checkHomework(String taskId, Map stuAnswer) {

        HomeWork homework = getHomeWork(taskId);

        HashMap<String, String> wrongMap = new HashMap<>();

        for (Select s : homework.getSelects()) {
            if (!s.getAnswer().equals(stuAnswer.get(s.getId()))) {
                wrongMap.put(s.getId(), s.getAnswer());
            }
        }

        for (TrueOrFalse s : homework.getTrueOrFalses()) {
            if (!s.getAnswer().equals(stuAnswer.get(s.getId()))) {
                wrongMap.put(s.getId(), s.getAnswer());
            }
        }

        // 此处还应有写数据库，写文件的操作。暂留明天写

        return wrongMap;
    }
}
