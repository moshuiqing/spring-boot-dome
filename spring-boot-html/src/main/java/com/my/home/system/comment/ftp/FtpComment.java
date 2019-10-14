package com.my.home.system.comment.ftp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.my.home.other.ftp.po.FilePath;
import com.my.home.other.util.Global;

/**
 * @author ai996 初始化加载配置文件ftp上传路径
 */
@Component
@Order(3)
public class FtpComment implements CommandLineRunner {
	
	@Autowired
	private EhCacheManager cacheManager;

	private static final Logger log = LoggerFactory.getLogger(FtpComment.class);

	@Override
	public void run(String... args) throws Exception {
		StringBuilder builder = new StringBuilder();
		// spring-boot 读取配置文件
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config/filepath.json");// 读取配置文件
		InputStreamReader reader = new InputStreamReader(in, "utf-8");

		BufferedReader brReader = new BufferedReader(reader);
		String content = null;
		while ((content = brReader.readLine()) != null) {
			builder.append(content);
		}
		brReader.close();		
		FilePath filePath = JSON.parseObject(builder.toString(), FilePath.class);
		Cache<String, Object>  sysCache=cacheManager.getCache("menuEacache");
		sysCache.put(Global.FILEPATHKEY, filePath);
		log.info("路劲存入缓存");

	}

}
