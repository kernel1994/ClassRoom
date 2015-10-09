package team.dx.classroom.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * ���ܣ�������ַ�����
 * ʵ��:����get��post��ʽ�ύ�ı���
 * */
public class CharacterEncodingFilter implements Filter {
	
	// ��������ļ���δָ���������ͣ�Ĭ�ϲ���UTF-8����
	private String defaultCharset = "UTF-8";
	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//��ȡweb.xml�����ļ������ñ���
		String charset = filterConfig.getInitParameter("charset");
		if (charset == null) {
			charset = defaultCharset;
		}
		
		//-----------post-----------
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);

		chain.doFilter(new MyRequest(request), response);
	}
	
	/* *
	 * ---------get---------
	 * ���ð�װģʽ��decorator������request���ܣ���
	 * ���request�ύ������get��ʽ�ύ�ʴ���ı����ʽ
	 * */
	class MyRequest extends HttpServletRequestWrapper {

		private HttpServletRequest request;
		
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		
		@Override
		public String getParameter(String name) {
			
			String value = request.getParameter(name);
			if (value == null) {
				return null;
			}
			if (!request.getMethod().equalsIgnoreCase("get")) {
				return value;
			}
			try {
				value = new String(value.getBytes("iso8859-1"), request.getCharacterEncoding());
				return value;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	@Override
	public void destroy() {
	}
	
}

