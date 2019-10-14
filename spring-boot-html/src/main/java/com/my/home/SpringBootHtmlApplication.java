package com.my.home;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@EnableRabbit  //队列
@ServletComponentScan //过滤器监听器
@SpringBootApplication
public class SpringBootHtmlApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(SpringBootHtmlApplication.class, args);
		try {
			ShowcaseWebsocketStarter.start();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
}


