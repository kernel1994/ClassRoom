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
 * ��Ӧ��¼�������ڲ�ͬ�û���ɫ
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** LoginService ���󣬸��𷵻ض�Ӧ��¼�Ķ���  */
	private PersonBusinessService personBusinessService = new PersonBusinessServiceImpl();
       
    public LoginServlet() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	/* ���û��� */
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	User user = personBusinessService.getUser(email, password);
    	
    	PrintWriter out = response.getWriter();
    	
    	// ���ؿ����ʾ��¼���ɹ�����������������Ajax + jQuery ������ʾ��Ϣ
    	if (user != null && user.getRole() != null) {
    		request.getSession().setAttribute("user", user);
        	
        	// �ض��򵽲�ͬ����û�����ҳ
        	// �涨�������ݿ��н�ɫrole ��id �ֶ�ֵΪ��ɫ������student etc.

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
