package com.my.home.system.lister;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.my.home.other.chat.config.ShowcaseServerConfig;
import com.my.home.other.util.Global;




@WebListener
public class BckLister implements ServletContextListener {
	


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Global.context = sce.getServletContext();// 获取全局上下文servletcontext
		//系统启动时执行
		//加载配置文件
		Properties p = new Properties();
		//spring-boot 读取配置文件
		InputStream sysin=this.getClass().getClassLoader().getResourceAsStream("config/system.properties");//读取配置文件
		try {
			p.load(sysin);
			Set<Entry<Object,Object>> entrySet = p.entrySet();
			if (entrySet.size() > 0) {
				for (Entry<Object, Object> entry : entrySet) {
					System.out.println(entry.getKey()+" -- "+entry.getValue());
					Global.context.setAttribute(entry.getKey().toString(), entry.getValue().toString());
					/*if(entry.getKey().equals("tcpPort")) {
						ShowcaseServerConfig.SERVER_PORT=Integer.parseInt(entry.getValue().toString());
					}*/
				}
		
			}			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("读取出错");
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//系统销毁时执行
		System.out.println("系统关闭");
		
		//ehCacheManager.destroy();//销毁缓存
		
	}
	
	


}
