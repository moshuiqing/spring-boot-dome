package com.my.home.system.webServelet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import com.alibaba.druid.support.http.StatViewServlet;



//配置durid图形界面登陆
@WebServlet(urlPatterns = { "/druid/*" }, initParams = { @WebInitParam(name = "loginUsername", value = "druid"),
		@WebInitParam(name = "loginPassword", value = "123456") })
public class DruidStatViewServlet extends StatViewServlet {
	private static final long serialVersionUID = 1L;
	
}