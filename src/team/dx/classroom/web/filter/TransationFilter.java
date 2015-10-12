package team.dx.classroom.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import team.dx.classroom.utils.JDBCUtils2;

/**
 * 功能：处理数据库事务
 * 实现: 执行servlet之前开启事务，执行完后提交并关闭事务
 * */
public class TransationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		try {
			//拦截下来后：获取连接、开启事务、并把连接绑定到当前线程
			//Connection connection = DBCUtils2.getConnection();如何这里得到了connection会往下传，很麻烦，要使用是直接调用DBCUtils2.getConnection();
			
			//如果执行语句中出现异常，则跳过下一句提交语句，数据自动回滚
			chain.doFilter(request, response);
			
			//获取当前线程上绑定的连接，提交事务，并关闭链接，释放连接与当前线程的绑定
			JDBCUtils2.commitTransaction();
		} finally {
			JDBCUtils2.closeConnection();
		}
		
		
	}

	@Override
	public void destroy() {
	}

}
