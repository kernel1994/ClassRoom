package team.dx.classroom.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import team.dx.classroom.dao.HomeWorkDAO;
import team.dx.classroom.dao.ResourceDAO;
import team.dx.classroom.dao.TaskDAO;
import team.dx.classroom.dao.ThirdPartyCommonDAO;
import team.dx.classroom.domain.*;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.HomeWorkService;
import team.dx.classroom.utils.XmlUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeWorkServiceImpl implements HomeWorkService {

    private TaskDAO tDAO = ObjectFactory.getInstance().createObject(TaskDAO.class);
    private ThirdPartyCommonDAO tpDAO = ObjectFactory.getInstance().createObject(ThirdPartyCommonDAO.class);
    private ResourceDAO rDAO = ObjectFactory.getInstance().createObject(ResourceDAO.class);
    private HomeWorkDAO hDAO = ObjectFactory.getInstance().createObject(HomeWorkDAO.class);

    /**
     * 获得task 所对应的文件URI
     * */
    private String getResourceURIByTaskId(String taskId) {

        String sqlT = "select * from task where id = ?";
        Task task = tDAO.getTask(sqlT, taskId);

        String sqlR = "select * from resource where id = ?";
        Resource resource = rDAO.getResource(sqlR, task.getResource_id());

        return resource.getUri();
    }

    @Override
    public HomeWork getHomeWork(String taskId) {

        String uri = getResourceURIByTaskId(taskId);

        HomeWork homework = hDAO.get(uri, null);

        return homework;
    }

    @Override
    public HashMap<String, String> checkHomework(String taskId, Map stuAnswer) {

        // 首先要将分数放进去才能累计
        Integer score = 0;
        stuAnswer.put("score", String.valueOf(score));
        HomeWork homework = getHomeWork(taskId);

        HashMap<String, String> wrongMap = new HashMap<String, String>();

        // 创建学生的作答保存的作业文件名+学生作答的xml 文件
        String taskResourceURI = getResourceURIByTaskId(taskId);
        String studentAnswerFileURI = "";
        try {
            studentAnswerFileURI = createStudentAnswerFile(taskResourceURI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String studentId = (String) stuAnswer.get("studentId");
        /** xml 标签不允许以数字开头，故添加前缀 */
        final String tagName = "stuId_" + studentId;

        // 读取文件成document 对象
        Document document = null;
        Element root = null;
        try {
            document = XmlUtils.parse(studentAnswerFileURI);
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 将学生的答案写入到文件中。<学号>答案</学号>
        /* 选择题 */
        for (Select s : homework.getSelects()) {
            // 插入学生答案
            insertStudentAnswer(s, stuAnswer, root, tagName);

            // 批改
            scoreTask(s, stuAnswer, wrongMap);
        }

        /* 判断题 */
        for (TrueOrFalse s : homework.getTrueOrFalses()) {
            // 插入学生答案
            insertStudentAnswer(s, stuAnswer, root, tagName);

            // 批改
            scoreTask(s, stuAnswer, wrongMap);
        }

        /* 简答题 */
        for (ShortQuestion s : homework.getShortQuestions()) {
            // 插入学生答案
            insertStudentAnswer(s, stuAnswer, root, tagName);

            // 批改
            scoreTask(s, stuAnswer, wrongMap);
        }

        // 还需要写评分，数据库，写文件

        try {
            XmlUtils.write(document, studentAnswerFileURI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将学生完成作业写入数据库
        String sScore = (String) stuAnswer.get("score");
        Integer iScore = Integer.parseInt(sScore);
        tpDAO.updateUserTask("insert", stuAnswer.get("studentId"),
                stuAnswer.get("taskId"), iScore);

        return wrongMap;
    }

    /**
     * 插入学生答案到文件中。已作答则改写
     * @param s 作业对象
     * @param stuAnswer 学生的答案
     * @param root 根节点
     * @param newTagName 插入的新节点名
     * */
    private void insertStudentAnswer(Topic s, Map stuAnswer, Element root, String newTagName) {

        String stuAns = (String) stuAnswer.get(s.getId());
        Element tag = XmlUtils.getElementByAttr(root, "id", s.getId());

        Element exist = tag.element(newTagName);
        if (exist != null) {
            exist.setText(stuAns);

            return;
        }

        tag.addElement(newTagName).setText(stuAns);
    }

    /**
     * 评分，并将学生回答错的题找出来. 将结果存储在wrongMap 中
     * */
    private void scoreTask(Topic s, Map stuAnswer, HashMap wrongMap) {

        // always return right answer, no matter student is correct
        if (!s.getAnswer().equalsIgnoreCase((String) stuAnswer.get(s.getId()))) {
            wrongMap.put(s.getId(), s.getAnswer());
        } else {
            wrongMap.put(s.getId(), s.getAnswer());
            // 对的题一题加5分 (code about the score just like shit!!!)
            String sScore = (String) stuAnswer.get("score");
            Integer iScore = Integer.parseInt(sScore);
            iScore += 5;
            stuAnswer.put("score", String.valueOf(iScore));
        }

    }

    /**
     * 创建存储学生的答案的xml 文件。文件名为："学生答案_" + 原文件名
     * 无则创建并返回URI，有则返回URI
     * @return studentAnswerFileURI 学生答案文件URI
     * */
    private String createStudentAnswerFile(String taskResourceURI) throws IOException {

        File taskFile = new File(taskResourceURI);
        String taskFileName = taskFile.getName();

        String studentAnswerFileName = "学生答案_" + taskFileName;
        String studentAnswerFileURI = taskResourceURI.replace(taskFileName, studentAnswerFileName);

        File studentAnswerFile = new File(studentAnswerFileURI);
        if (studentAnswerFile.exists()) {
            return studentAnswerFileURI;
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;
        fis = new FileInputStream(taskFile);
        fos = new FileOutputStream(studentAnswerFileURI);

        int reader = -1;
        byte[] readByte = new byte[1024];
        while ((reader = fis.read(readByte)) != -1) {
            fos.write(readByte, 0, reader);
        }

        if (fis != null) {
            fis.close();
        }

        if (fos != null) {
            fos.close();
        }

        return studentAnswerFileURI;
    }

    /**
     * 获得学生存储作业的文件UIR
     * */
    private String getStudentHomeworkURIByTaskId(String taskId) {

        String taskResourceURI = getResourceURIByTaskId(taskId);
        String studentHomeworkURI = null;

        try {
            studentHomeworkURI = createStudentAnswerFile(taskResourceURI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentHomeworkURI;
    }

    @Override
    public HomeWork getStudentHomeWork(String taskId, String studentId) {

        String studentHomeworkURI = getStudentHomeworkURIByTaskId(taskId);

        return hDAO.get(studentHomeworkURI, studentId);
    }

}
