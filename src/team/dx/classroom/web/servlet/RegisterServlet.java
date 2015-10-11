package team.dx.classroom.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.User;
import team.dx.classroom.utils.WebUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//��װuserע����Ϣ
			User user = WebUtils.request2Bean(request.getParameterMap(), User.class);
			user.setId(WebUtils.getRandomUUID());
			user.setBirthday(WebUtils.birthdayUtils(request.getParameterMap()));
			
			
			
			request.setAttribute("message", "ע��ɹ�");
		} catch (Exception e) {
			request.setAttribute("message", "ע��ʧ��");
		}
		
		request.getRequestDispatcher("/message.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
