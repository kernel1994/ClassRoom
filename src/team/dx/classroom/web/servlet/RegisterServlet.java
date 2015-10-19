package team.dx.classroom.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.dx.classroom.domain.Role;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.PersonBusinessService;
import team.dx.classroom.service.RoleService;
import team.dx.classroom.utils.WebUtils;

/* *
 * 处理注册逻辑信息
 * 
 * */
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PersonBusinessService pbs = ObjectFactory.getInstance()
			.createObject(PersonBusinessService.class);
	private RoleService rs = ObjectFactory.getInstance().createObject(
			RoleService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 封装user注册信息
			User user = WebUtils.request2Bean(request.getParameterMap(),
					User.class);
			user.setId(WebUtils.getRandomUUID());
			user.setBirthday(WebUtils.birthdayUtils(request.getParameterMap()));

			// 检测user数据合法性----待实现

			/*--------------读取配置文件中的参数-----------------*/
			String role_id = this.getServletConfig()
					.getInitParameter("role_id");

			// 给用户添加角色
			Role role = rs.getRole(role_id);

			user.setRole(role);
			pbs.addUser(user);

			// 注册成功后将user放入session中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// 显示注册成功，然后再跳转到主页面
			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/student/index.jsp");
			request.setAttribute("message", "注册成功");

		} catch (Exception e) {
			request.setAttribute("message", "注册失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
