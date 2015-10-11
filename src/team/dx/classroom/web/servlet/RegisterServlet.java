package team.dx.classroom.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Role;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.PersonBusinessService;
import team.dx.classroom.utils.WebUtils;
/* *
 * 处理注册逻辑信息
 * 
 * */
public class RegisterServlet extends HttpServlet {

	static final long serialVersionUID = 1L;
	PersonBusinessService pbs = ObjectFactory.getInstance().createObject(PersonBusinessService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//封装user注册信息
			User user = WebUtils.request2Bean(request.getParameterMap(), User.class);
			user.setId(WebUtils.getRandomUUID());
			user.setBirthday(WebUtils.birthdayUtils(request.getParameterMap()));
	
			//检测user数据合法性----待实现
			
			//给用户添加角色
			Role role = new Role();
			
			
			user.setRole(role);
			pbs.addUser(user);
			
			request.setAttribute("message", "注册成功");
		} catch (Exception e) {
			request.setAttribute("message", "注册失败");
		}
		
		request.getRequestDispatcher("/message.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
