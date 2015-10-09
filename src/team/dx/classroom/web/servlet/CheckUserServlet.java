package team.dx.classroom.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.PersonBusinessService;

public class CheckUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nick = request.getParameter("nick");
		
		PersonBusinessService pbs = ObjectFactory.getInstance().createObject(PersonBusinessService.class);
		User user = pbs.findUserIsExist(nick);
		if (user != null) {
			response.getWriter().write("该账号已经存在");
		}
		else {
			response.getWriter().write("注册成功");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
