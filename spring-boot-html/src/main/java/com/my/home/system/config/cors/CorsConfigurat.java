package com.my.home.system.config.cors;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ai996
 * 跨域
 */
@Configuration
public class CorsConfigurat extends WebMvcConfigurerAdapter {
	
	@Value(value = "${MYCorsURL}")
	String url;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println(url);
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
						.allowCredentials(true);
			}
		};
	}

}
