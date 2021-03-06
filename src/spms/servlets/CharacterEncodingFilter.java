package spms.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	FilterConfig config;


	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
			throws IOException, ServletException {
		
		/* 서블릿이 실행되기 전에 해야 할 작업 */
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		nextFilter.doFilter(request, response);
		System.out.println("인코딩설정");
		/* 서블릿이 실행한 후, 클라이언트에게 응답하기 전에 해야할 작업 */
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub	
	}
}
