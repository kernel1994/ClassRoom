package team.dx.classroom.web.servlet;

import java.io.IOException;
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
public class MethodInvokeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /ClassRoom/servlet/RoleServlet?method=getAll
		String methodName = request.getParameter("method");
		
		if (methodName == null) {
			throw new RuntimeException("没有指定方法");
		}

		try {
			// 获取与methodName对应的方法
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			
			// 抑制Java的访问控制检查
			// 如果不设置为true，将会Error: TestPrivate can not access a member of class PrivateClass with modifiers "private"
			method.setAccessible(true);
			
			// 利用反射来调用方法
			method.invoke(this, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类MethodInvokeServlet2出现未知异常");
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
