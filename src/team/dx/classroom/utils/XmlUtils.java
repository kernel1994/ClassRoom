package team.dx.classroom.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {

	/*
	 * public static Document parse(URL url) throws DocumentException {
	 * SAXReader reader = new SAXReader(); Document document = reader.read(url);
	 * return document; }
	 */

	// һ��ֱ�Ӵ�·��,Ҳ���Կ�����URL����
	public static Document parse(String path) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(path);
		return document;
	}

	public static void write(Document document, String path) throws IOException {
		// ����һ����д��ʽ����Ȼ������
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		// д���ļ�
		XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
		writer.write(document);

		writer.close();
	}
}
