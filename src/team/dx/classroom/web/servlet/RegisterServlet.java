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
 * ����ע���߼���Ϣ
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
			// ��װuserע����Ϣ
			User user = WebUtils.request2Bean(request.getParameterMap(),
					User.class);
			user.setId(WebUtils.getRandomUUID());
			user.setBirthday(WebUtils.birthdayUtils(request.getParameterMap()));

			// ���user���ݺϷ���----��ʵ��

			/*--------------��ȡ�����ļ��еĲ���-----------------*/
			String role_id = this.getServletConfig()
					.getInitParameter("role_id");

			// ���û���ӽ�ɫ
			Role role = rs.getRole(role_id);

			user.setRole(role);
			pbs.addUser(user);

			// ע��ɹ���user����session��
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// ��ʾע��ɹ���Ȼ������ת����ҳ��
			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/student/index.jsp");
			request.setAttribute("message", "ע��ɹ�");

		} catch (Exception e) {
			request.setAttribute("message", "ע��ʧ��");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
