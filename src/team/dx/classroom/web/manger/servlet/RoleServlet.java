package team.dx.classroom.web.manger.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.dx.classroom.domain.Role;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.PersonBusinessService;
import team.dx.classroom.service.RoleService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

/**
 * RoleServlet��extendsMethodInvokeServlet ����insert delete �����Խ�ɫ�߼�����
 * 
 * */
@SuppressWarnings("all")
public class RoleServlet extends MethodInvokeServlet2 {
	
	private static final long serialVersionUID = 1L;
	private RoleService rs = ObjectFactory.getInstance().createObject(
			RoleService.class);
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Role> roles = rs.getAllRoles();
			request.setAttribute("roles", roles);
			request.getRequestDispatcher("/admin/manager/listrole.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "RoleServlet����getAll���������쳣");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}

	}
	
	public void addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("/admin/manager/addrole.jsp").forward(request, response);
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Role role = WebUtils.request2Bean(request.getParameterMap(), Role.class);
			rs.addRole(role);
			
			request.setAttribute("message", "��ӳɹ�");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "���ʧ��");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
}
