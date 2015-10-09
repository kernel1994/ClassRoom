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
 * 响应登录请求，用于不同用户角色
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** LoginService 对象，负责返回对应登录的对象  */
	private LoginService loginService = new LoginService();
       
    public LoginServlet() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	User user = loginService.getUser(email, password);
    	
    	PrintWriter out = response.getWriter();
    	
    	// 返回空则表示登录不成功，密码或邮箱错误，用Ajax + jQuery 给出提示信息
    	if (user != null) {
    		request.getSession().setAttribute("user", user);
        	
        	String role = "";
        	if (user.getRole().getName().equals("学生")) {
        		role = "student";
        	} else if (user.getRole().getName().equals("教师")) {
        		role = "teacher";
        	} else if (user.getRole().getName().equals("管理员")) {
        		role = "admin";
        	}
        	
        	// 重定向到不同身份用户的主页
        	out.write(request.getContextPath() + "/" + role + "/index.jsp");
    	} else {
    		out.write("NO");
    	}
    	
    	out.flush();
		out.close();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
