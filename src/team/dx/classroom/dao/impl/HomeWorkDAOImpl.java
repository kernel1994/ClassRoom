package team.dx.classroom.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import team.dx.classroom.dao.HomeWorkDAO;
import team.dx.classroom.domain.HomeWork;
import team.dx.classroom.domain.Select;
import team.dx.classroom.domain.ShortQuestion;
import team.dx.classroom.domain.TrueOrFalse;
import team.dx.classroom.utils.XmlUtils;

public class HomeWorkDAOImpl implements HomeWorkDAO {

	@Override
	public void add(HomeWork homeWork, String path, String standardPath) {
		try {
			//得到一个xml，进行编辑
			Document document = XmlUtils.parse(standardPath);
			Element root = document.getRootElement();
			
			List<Select> selects = homeWork.getSelects();
			List<TrueOrFalse> trueOrFalses = homeWork.getTrueOrFalses();
			List<ShortQuestion> shortQuestions = homeWork.getShortQuestions();
			
			int i = 1;
			if (selects != null && selects.size() != 0) {
				for (Select select : selects) {
					Element selectTag = root.addElement( "select" );
					selectTag.addAttribute( "id", "select" + i++ );
					selectTag.addAttribute( "answer", select.getAnswer() );
					selectTag.addElement("title").setText(select.getTitle());
					selectTag.addElement("A").setText(select.getA());
					selectTag.addElement("B").setText(select.getB());
					selectTag.addElement("C").setText(select.getC());
					selectTag.addElement("D").setText(select.getD());
					selectTag.addElement("description").setText(select.getDescription());
				}
			}
			
			i = 1;
			if (trueOrFalses != null && trueOrFalses.size() != 0) {
				for (TrueOrFalse trueOrFalse : trueOrFalses) {
					Element trueOrFalseTag = root.addElement( "trueorfalse" );
					trueOrFalseTag.addAttribute( "id", "trueorfalse" + i++ );
					trueOrFalseTag.addAttribute( "answer", trueOrFalse.getAnswer());
					trueOrFalseTag.addElement("title").setText(trueOrFalse.getTitle());
					trueOrFalseTag.addElement("description").setText(trueOrFalse.getDescription());
			
				}
			}
			
			i = 1;
			if (shortQuestions != null && shortQuestions.size() != 0) {
				for (ShortQuestion shortQuestion : shortQuestions) {
					Element shortQuestionTag = root.addElement( "shortquestion" );
					shortQuestionTag.addAttribute( "id", "shortquestion" + i++ );
					shortQuestionTag.addElement("title").setText(shortQuestion.getTitle());
					shortQuestionTag.addElement("description").setText(shortQuestion.getDescription());
				}
			}
			
			/*----------将编辑好的xml写入硬盘--------*/
			XmlUtils.write(document,path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public HomeWork get(String path, String studentId) {

		try {
			// team/dx/classroom/service/impl/HomeWorkServiceImpl.java 提到过。是因为xml 标签不允许数字开头，故加前缀
			studentId = "stuId_" + studentId;

			Document document = XmlUtils.parse(path);

			Element root = document.getRootElement();

			/* 选择题 */
			List<Select> sDms = new ArrayList<Select>();
			for (Iterator i = root.elementIterator("select"); i.hasNext(); ) {
				Element select = (Element)i.next();

				Select sDm = new Select();

				String id = select.attributeValue("id");
				sDm.setId(id);

				String answer = select.attributeValue("answer");
				sDm.setAnswer(answer);

				String stuAnswer = select.elementText(studentId);
				sDm.setStuAnswer(stuAnswer);

				String title = select.elementText("title");
				sDm.setTitle(title);

				String description = select.elementText("description");
				sDm.setDescription(description);

				String A = select.elementText("A");
				sDm.setA(A);

				String B = select.elementText("B");
				sDm.setB(B);

				String C = select.elementText("C");
				sDm.setC(C);

				String D = select.elementText("D");
				sDm.setD(D);

				sDms.add(sDm);
			}

			/* 判断题 */
			List<TrueOrFalse> tfDms = new ArrayList<TrueOrFalse>();
			for (Iterator i = root.elementIterator("trueorfalse"); i.hasNext(); ) {
				Element trueorfalse = (Element)i.next();

				TrueOrFalse tfDm = new TrueOrFalse();

				String id = trueorfalse.attributeValue("id");
				tfDm.setId(id);

				String answer = trueorfalse.attributeValue("answer");
				tfDm.setAnswer(answer);

				String stuAnswer = trueorfalse.elementText(studentId);
				tfDm.setStuAnswer(stuAnswer);

				String title = trueorfalse.elementText("title");
				tfDm.setTitle(title);

				String description = trueorfalse.elementText("description");
				tfDm.setDescription(description);

				tfDms.add(tfDm);
			}

			/* 简答题 */
			List<ShortQuestion> sqDms = new ArrayList<ShortQuestion>();
			for (Iterator i = root.elementIterator("shortquestion"); i.hasNext(); ) {
				Element shortquestion = (Element)i.next();

				ShortQuestion sqDm = new ShortQuestion();

				String id = shortquestion.attributeValue("id");
				sqDm.setId(id);

				String stuAnswer = shortquestion.elementText(studentId);
				sqDm.setStuAnswer(stuAnswer);

				String title = shortquestion.elementText("title");
				sqDm.setTitle(title);

				String description = shortquestion.elementText("description");
				sqDm.setDescription(description);

				sqDms.add(sqDm);
			}


			HomeWork homeWork = new HomeWork();
			homeWork.setSelects(sDms);
			homeWork.setTrueOrFalses(tfDms);
			homeWork.setShortQuestions(sqDms);

			return homeWork;
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return null;
	}
}
