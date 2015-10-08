package team.dx.classroom.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MethodInvokeServlet<br />
 * 拥有doProcess 方法。其作用是利用Java反射机制，进行方法调用method.invoke()<br />
 * 这样就很方便得进行方法的扩展。子类可以继承。实现自己的方法调用。
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

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置请求的字符集
		request.setCharacterEncoding("UTF-8");

		// 获取的是这样的 /deleteCustomer.do /addCustomer.do
		String servletPath = request.getServletPath();

		/*
		 * 路径是这样构成的 http://localhost:8080/工程名/文件夹名/(子文件夹)/文件.jsp或请求名.do
		 * servletPath 是 /文件夹名/(子文件夹)/文件或请求名 ContextPath 是 /工程名 RequestURL 是
		 * /工程名/文件夹名/(子文件夹)/文件或请求名 截取servletPath的最后一个/ 和 .ado 之间的字符串
		 */
		String methodName = servletPath.substring(servletPath.lastIndexOf("/") + 1, servletPath.length() - 4);

		try {
			// 获取与methodName对应的方法
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			
			// 抑制Java的访问控制检查
			// 如果不设置为true，将会Error: TestPrivate can not access a member of class PrivateClass with modifiers "private"
			method.setAccessible(true);
			
			// 利用反射来调用方法
			method.invoke(this, request, response);

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
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
