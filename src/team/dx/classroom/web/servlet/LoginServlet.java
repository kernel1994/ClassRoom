package team.dx.classroom.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.User;
import team.dx.classroom.service.PersonBusinessService;
import team.dx.classroom.service.impl.PersonBusinessServiceImpl;

/**
 * 响应登录请求，用于不同用户角色
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** LoginService 对象，负责返回对应登录的对象  */
	private PersonBusinessService personBusinessService = new PersonBusinessServiceImpl();
       
    public LoginServlet() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	/* 禁用缓存 */
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	User user = personBusinessService.getUser(email, password);
    	
    	PrintWriter out = response.getWriter();
    	
    	// 返回空则表示登录不成功，密码或邮箱错误，用Ajax + jQuery 给出提示信息
    	if (user != null && user.getRole() != null) {
    		request.getSession().setAttribute("user", user);
        	
        	// 重定向到不同身份用户的主页
        	// 规定：在数据库中角色role 表id 字段值为角色名，即student etc.

    		if (user.getRole().getId().equals("student")) {
    			out.write("createIndex.studentdo");
    		} else if ("teacher".equals(user.getRole().getId())) {
    			out.write(request.getContextPath() + "/servlet/TeacherIndexServlet");
    		} else {
    			out.write(request.getContextPath() + "/" + user.getRole().getId() + "/index.jsp");
    		}

    	} else if(user != null && user.getRole() == null) {
    		out.write(request.getContextPath() + "/index.jsp");
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
