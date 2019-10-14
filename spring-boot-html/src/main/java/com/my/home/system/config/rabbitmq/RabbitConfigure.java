package com.my.home.system.config.rabbitmq;
/*package com.my.home.system.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration   //定义配置类
public class RabbitConfigure {
	
	//队列名称
	public final static String SPRING_BOOT_QUEUE="spring";
	//交换机名称
	public final static String SPRING_BOOT_EXCHANGE="exchange";
	//绑定的值
	public static final String SPRING_BOOT_BIND_KEY="key";
	
	*//**
	 * @return
	 * 1定义队列
	 *//*
	@Bean 
	Queue queue() {
		return new Queue(SPRING_BOOT_QUEUE,false);
	}
	
	*//**
	 * @return
	 * 1定义交换机
	 *//*
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(SPRING_BOOT_EXCHANGE);
	}
	
	*//**
	 * @param queue
	 * @param exchange
	 * @return
	 * 1定义绑定
	 *//*
	@Bean
	Binding binding(Queue queue,TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(SPRING_BOOT_BIND_KEY);
	}
	
	*//**
	 * @return
	 * 1定义消息转换实例
	 *//*
	@Bean
	MessageConverter messageConverter() {
		
		 return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
	}
	
	
}
*/