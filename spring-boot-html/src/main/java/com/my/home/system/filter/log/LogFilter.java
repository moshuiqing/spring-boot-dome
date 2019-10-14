package com.my.home.system.filter.log;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.my.home.other.util.systemutil.DateUtils;
import com.my.home.other.util.systemutil.SystemUtils;
import com.my.home.system.po.SysUser;
import com.my.home.system.po.Syslog;
import com.my.home.system.service.SysLogService;

@WebFilter
public class LogFilter implements Filter {

	@Autowired
	private SysLogService sysLogService;

	private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("开始");

	}

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取请求地址
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
				
		String ip = SystemUtils.getIpAddress(httpRequest); // 请求ip
		
		String a= (String) request.getAttribute("user");
		String url = httpRequest.getRequestURL().toString();// 请求地址
		if (url.indexOf("found") > 0 ){
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		String method = httpRequest.getMethod();
		// 请求时的系统时间
		LocalTime time1 = LocalTime.now();
		String isAbnormal = "正常";

		try {
			chain.doFilter(httpRequest, httpResponse);
			if(httpRequest.getAttribute("isAbnormal")!=null) {
				isAbnormal=(String) httpRequest.getAttribute("isAbnormal");
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("异常");
			isAbnormal = "异常";
		}

		// 响应时的系统时间
		LocalTime time2 = LocalTime.now();
		// 计算请求响应耗时
		Duration total = Duration.between(time1, time2);
		String timeConsuming = total.toMillis() + "";
		System.out.println(url + "耗时：" + timeConsuming + "----------" + ip);
		SysUser user = new SysUser();
		Subject subject = SecurityUtils.getSubject();
		Object key = subject.getPrincipal();
		if(key!=null) {
			
			try {
				BeanUtils.copyProperties(user, key);
			} catch (Exception e) {

			}
			
		}
		
		
		String name = "";
		if (user != null) {
			name = user.getUsername();
		}
		Syslog syslog = new Syslog();
		syslog.setUrl(url);
		syslog.setIp(ip);
		syslog.setIsAbnormal(isAbnormal);
		syslog.setMethod(method);
		syslog.setOperator(name);
		syslog.setTimeConsuming(timeConsuming);
		syslog.setOperatingTime(DateUtils.backDate(new Date()));
		sysLogService.insert(syslog);
		
		

	}

	@Override
	public void destroy() {
		log.info("结束");

	}

}
