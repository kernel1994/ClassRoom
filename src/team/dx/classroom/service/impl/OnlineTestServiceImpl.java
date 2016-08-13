package team.dx.classroom.service.impl;

import team.dx.classroom.dao.*;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Resource;
import team.dx.classroom.domain.Task;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.OnlineTestService;
import team.dx.classroom.utils.WebUtils;

import java.io.File;
import java.util.Date;

/**
 * Created by kernel on 2016/8/12.
 */
public class OnlineTestServiceImpl implements OnlineTestService {
    private ExamsDAO eDAO = ObjectFactory.getInstance().createObject(ExamsDAO.class);
    private ResourceDAO rDAO = ObjectFactory.getInstance().createObject(ResourceDAO.class);
    private TaskDAO tDAO = ObjectFactory.getInstance().createObject(TaskDAO.class);
    private HomeWorkDAO hDAO = ObjectFactory.getInstance().createObject(HomeWorkDAO.class);
    private ThirdPartyCommonDAO tpDAO = ObjectFactory.getInstance().createObject(ThirdPartyCommonDAO.class);

    @Override
    public String createStanderOnlineTest(String userID, String homeworkDir, String templateXMLPath) {
        return  createCustomOnlineTest(userID, homeworkDir, templateXMLPath, "0", "0", "", "0", "5");
    }

    @Override
    public String createCustomOnlineTest(String uerID, String homeworkDir, String templateXMLPath,
                                       String chapter, String degree, String knowledgepoint,
                                       String type, String examcount) {
        // 对输入数据处理
        if ("0".equals(chapter)) {
            chapter = "%";
        }

        if ("0".equals(degree)) {
            degree = "%";
        }

        if ("".equals(knowledgepoint)) {
            knowledgepoint = "%";
        }

        if ("0".equals(type)) {
            type = "%";
        }

        if ("0".equals(examcount) || "".equals(examcount)) {
            //默认每个类型（若没有选择类型）读取两个题目
            examcount = "2";
        }
        HomeWork homeWork = eDAO.getExams(chapter, degree, knowledgepoint, type, examcount);

        // 0. 先构造资源对象中有关文件写入的部分属性
        Resource resource = new Resource();
        resource.setId(WebUtils.getRandomUUID());
        resource.setName("在线测试_" + uerID + "_" + resource.getId() + ".xml");
        resource.setUri(homeworkDir + File.separator + resource.getName());

        // 1. 将在线测试写入 XML 文件中
        hDAO.add(homeWork, resource.getUri(), templateXMLPath);

        // 2. 将保存在 XML 文件中的测试封装成一个资源，写到数据库
        resource.setUploadtime(new Date());
        resource.setDescription("在线测试_" + uerID);
        // 在线测试题生成者的 ID
        User uploader = new User();
        uploader.setId(uerID);
        resource.setUploader(uploader);
        // 写入数据库
        rDAO.addResource(resource);

        // 3. 将上一步得到的资源，封装成一次 Task，写到数据库
        Task task = new Task();
        task.setId(WebUtils.getRandomUUID());
        task.setName(resource.getName());
        task.setResource(resource);
        // 写入数据库, 第二个参数是 course, 表示作业所对应所属的课程，
        // 此处是在线测试生成的作业没有课程对应，暂时写为 计算机组成原理
        // TODO : feature: 在线测试的课程归属
        tDAO.addTask(task, "d236b5cd-6533-45b2-925f-b96fae1ce6bf");

        return task.getId();
    }
}
