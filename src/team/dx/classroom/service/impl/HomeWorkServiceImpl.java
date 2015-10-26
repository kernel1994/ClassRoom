package team.dx.classroom.service.impl;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import team.dx.classroom.dao.HomeWorkDAO;
import team.dx.classroom.dao.ResourceDAO;
import team.dx.classroom.dao.TaskDAO;
import team.dx.classroom.domain.*;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.HomeWorkService;
import team.dx.classroom.utils.XmlUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HomeWorkServiceImpl implements HomeWorkService {

    private TaskDAO tDAO = ObjectFactory.getInstance().createObject(TaskDAO.class);
    private ResourceDAO rDAO = ObjectFactory.getInstance().createObject(ResourceDAO.class);
    private HomeWorkDAO hDAO = ObjectFactory.getInstance().createObject(HomeWorkDAO.class);

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

        HomeWork homework = hDAO.get(uri);

        return homework;
    }

    @Override
    public HashMap<String, String> checkHomework(String taskId, Map stuAnswer) {

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

        String studentId = (String) stuAnswer.get("studentId");
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
            String stuAns = (String) stuAnswer.get(s.getId());
            Element tag = getElementByAttr(root, "id", s.getId());
            // xml 标签不允许以数字开头，故添加前缀
            tag.addElement("stuId_" + studentId).setText(stuAns);

            // 批改
            if (!s.getAnswer().equalsIgnoreCase((String) stuAnswer.get(s.getId()))) {
                wrongMap.put(s.getId(), s.getAnswer());
            }
        }

        /* 判断题 */
        for (TrueOrFalse s : homework.getTrueOrFalses()) {
            // 插入学生答案
            String stuAns = (String) stuAnswer.get(s.getId());
            Element tag = getElementByAttr(root, "id", s.getId());
            // xml 标签不允许以数字开头，故添加前缀
            tag.addElement("stuId_" + studentId).setText(stuAns);

            // 批改
            if (!s.getAnswer().equalsIgnoreCase((String) stuAnswer.get(s.getId()))) {
                wrongMap.put(s.getId(), s.getAnswer());
            }
        }

        /* 简答题 */
        for (ShortQuestion s : homework.getShortQuestions()) {
            // 插入学生答案
            String stuAns = (String) stuAnswer.get(s.getId());
            Element tag = getElementByAttr(root, "id", s.getId());
            // xml 标签不允许以数字开头，故添加前缀
            tag.addElement("stuId_" + studentId).setText(stuAns);
        }

        // 还需要写评分，数据库，写文件

        try {
            XmlUtils.write(document, studentAnswerFileURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wrongMap;
    }

    /**
     * 获取属性type 为val 的节点
     * */
    public Element getElementByAttr(Element node , String type , String val) {
        for (Iterator iter = node.elementIterator(); iter.hasNext();) {
            Element element = (Element) iter.next();
            Attribute name = element.attribute(type);
            if (name != null) {
                String value = name.getValue();
                if (value != null && val.equals(value))
                    return element;
                else
                    getElementByAttr(element, type, val);
            }
        }
        return null;
    }

    /**
     * 创建存储学生的答案的xml 文件。文件名为："学生答案_" + 原文件名
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
}
