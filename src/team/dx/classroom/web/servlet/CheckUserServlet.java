package team.dx.classroom.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.PersonBusinessService;
/**
 * AJAX异步检测使用是否已经注册
 */
public class CheckUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonBusinessService pbs = ObjectFactory.getInstance().createObject(PersonBusinessService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nick = request.getParameter("nick");
		Boolean isTrue = pbs.findUserIsExist(nick);
		
		if (isTrue) {
			response.getWriter().write("该账号可用");
		}
		else {
			response.getWriter().write("该账号已经存在");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
