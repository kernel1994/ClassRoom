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
 * ���ܣ��������ݿ�����
 * ʵ��: ִ��servlet֮ǰ��������ִ������ύ���ر�����
 * */
public class TransationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		try {
			//���������󣺻�ȡ���ӡ��������񡢲������Ӱ󶨵���ǰ�߳�
			//Connection connection = DBCUtils2.getConnection();�������õ���connection�����´������鷳��Ҫʹ����ֱ�ӵ���DBCUtils2.getConnection();
			
			//���ִ������г����쳣����������һ���ύ��䣬�����Զ��ع�
			chain.doFilter(request, response);
			
			//��ȡ��ǰ�߳��ϰ󶨵����ӣ��ύ���񣬲��ر����ӣ��ͷ������뵱ǰ�̵߳İ�
			JDBCUtils2.commitTransaction();
		} finally {
			JDBCUtils2.closeConnection();
		}
		
		
	}

	@Override
	public void destroy() {
	}

}
