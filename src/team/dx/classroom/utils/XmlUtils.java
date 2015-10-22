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

	// 一般直接传路径,也可以考虑用URL对象
	public static Document parse(String path) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(path);
		return document;
	}

	public static void write(Document document, String path) throws IOException {
		// 设置一个书写格式，不然无缩进
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		// 写进文件
		XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
		writer.write(document);

		writer.close();
	}
}
