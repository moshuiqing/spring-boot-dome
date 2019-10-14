package com.my.home.system.comment.menuModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.my.home.system.service.MenusService;

/**
 * @author ai996
 *  自定义初始化加载  （在所有配置类加载完成之后加载）
 */
@Component
@Order(2)
public class CacheMenuModule implements CommandLineRunner {
	
	
	private static final Logger log = LoggerFactory.getLogger(CacheMenuModule.class);
	@Autowired
	private MenusService menusService;

	@Override
	public void run(String... args) throws Exception {
		
		try {
			menusService.cacheMenuModule();
			log.info("菜单缓存");
		} catch (Exception e) {
			log.info("缓存失败");
			e.printStackTrace();
		}
		
	}

}
