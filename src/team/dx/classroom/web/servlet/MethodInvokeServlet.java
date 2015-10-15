package team.dx.classroom.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MethodInvokeServlet<br />
 * ӵ��doProcess ������������������Java������ƣ����з�������method.invoke()<br />
 * �����ͺܷ���ý��з�������չ��������Լ̳С�ʵ���Լ��ķ������á�
 */
public class MethodInvokeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MethodInvokeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��������ƥ���׺�ĳ��ȡ����������Ҫ���Ǵ˷�����
	 * */
	public int getSuffixLen() {
		
		return ".dao".length();
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ȡ���������� /deleteCustomer.do /addCustomer.do
		String servletPath = request.getServletPath();

		/*
		 * ·�����������ɵ� http://localhost:8080/������/�ļ�����/(���ļ���)/�ļ�.jsp��������.do
		 * servletPath �� /�ļ�����/(���ļ���)/�ļ��������� ContextPath �� /������ RequestURL ��
		 * /������/�ļ�����/(���ļ���)/�ļ��������� ��ȡservletPath�����һ��/ �� .ado ֮����ַ���
		 */
		String methodName = servletPath.substring(servletPath.lastIndexOf("/") + 1, servletPath.length() - getSuffixLen());

		try {
			// ��ȡ��methodName��Ӧ�ķ���
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			
			// ����Java�ķ��ʿ��Ƽ��
			// ���������Ϊtrue������Error: TestPrivate can not access a member of class PrivateClass with modifiers "private"
			method.setAccessible(true);
			
			// ���÷��������÷���
			method.invoke(this, request, response);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

}
