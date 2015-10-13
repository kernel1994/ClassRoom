package team.dx.classroom.web.servlet;

import java.io.IOException;
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
public class MethodInvokeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /ClassRoom/servlet/RoleServlet?method=getAll
		String methodName = request.getParameter("method");
		
		if (methodName == null) {
			throw new RuntimeException("û��ָ������");
		}

		try {
			// ��ȡ��methodName��Ӧ�ķ���
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			
			// ����Java�ķ��ʿ��Ƽ��
			// ���������Ϊtrue������Error: TestPrivate can not access a member of class PrivateClass with modifiers "private"
			method.setAccessible(true);
			
			// ���÷��������÷���
			method.invoke(this, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��MethodInvokeServlet2����δ֪�쳣");
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
