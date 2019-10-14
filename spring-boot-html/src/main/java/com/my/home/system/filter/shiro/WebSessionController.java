package com.my.home.system.filter.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.my.home.other.util.Global;

public class WebSessionController extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		HttpServletRequest req = (HttpServletRequest) request;
		Object obj = req.getSession().getAttribute(Global.loginType);
		if(obj!=null) {
			if(!obj.toString().equals(Global.web)) {
				subject.logout();
				//WebUtils.issueRedirect(request, response, "/webdesk/login/toWebLogin");
				return true;
			}
		}
	
		
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		
		return true;
	}

}
