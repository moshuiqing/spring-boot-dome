/*package com.my.home.system.filter.back;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;

import com.my.home.other.util.Global;




@WebFilter(urlPatterns="/backsystem/*")//@WebFilter是定义过滤器的注解 ，urlPatterns="/*" 定义过滤器过滤的路径
public class BackFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器被初始化");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		//HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getRequestURL().toString();
		if(url.contains("/backsystem/index/login")) {
			chain.doFilter(request, response);  
		}else {

			Object user= httpRequest.getSession().getAttribute(Global.sysUser);
			
			if(user!=null){
				chain.doFilter(request, response);                                                 
			}else{                              
				//跳转回首页
				httpRequest.getRequestDispatcher("/").forward(request, response);
			}
			
		}
		
	
		
		
	}

	@Override
	public void destroy() {
		System.out.println("销毁");
		
	}

}*/