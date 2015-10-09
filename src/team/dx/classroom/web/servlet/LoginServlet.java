package team.dx.classroom.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.User;
import team.dx.classroom.service.LoginService;

/**
 * ��Ӧ��¼�������ڲ�ͬ�û���ɫ
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** LoginService ���󣬸��𷵻ض�Ӧ��¼�Ķ���  */
	private LoginService loginService = new LoginService();
       
    public LoginServlet() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	User user = loginService.getUser(email, password);
    	
    	// ���ؿ����ʾ��¼���ɹ�����������������ʱ���������Ժ���Ajax + jQuery ����
    	if (user == null) {
    		// response.sendRedirect(request.getContextPath() + "/login.jsp");
    		PrintWriter out = response.getWriter();
    		out.write("NO");
    		
    		out.flush();
    		out.close();
    		return;
    	}
    	
    	request.getSession().setAttribute("user", user);
    	
    	String role = "";
    	if (user.getRole().getName().equals("ѧ��")) {
    		role = "student";
    	} else if (user.getRole().getName().equals("��ʦ")) {
    		role = "teacher";
    	} else if (user.getRole().getName().equals("����Ա")) {
    		role = "admin";
    	}
    	
    	// �ض��򵽲�ͬ����û�����ҳ
		response.sendRedirect(request.getContextPath() + "/" + role + "/index.jsp");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
