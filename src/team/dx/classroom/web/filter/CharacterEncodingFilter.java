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
 * 功能：处理的字符编码
 * 实现:处理get与post方式提交的编码
 * */
public class CharacterEncodingFilter implements Filter {
	
	// 如何配置文件中未指定编码类型，默认采用UTF-8编码
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
		
		//获取web.xml配置文件中配置编码
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
	 * 采用包装模式（decorator）增加request功能，即
	 * 解决request提交数据中get方式提交问处理的编码格式
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

