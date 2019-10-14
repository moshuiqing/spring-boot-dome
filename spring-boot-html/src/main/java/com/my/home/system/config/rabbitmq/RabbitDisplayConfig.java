package com.my.home.system.config.rabbitmq;
/*package com.my.home.system.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDisplayConfig {
	
	@Bean
	public CustomExchange delayExchange() {		
		Map<String, Object> map = new HashMap<>();
		map.put("x-delayed-type", "direct");
		return new CustomExchange("test_exchange","x-delayed-message", true, false, map);
	}
	@Bean
	public Queue queue() {		
		Queue queue = new Queue("test_queue",true);
		return queue;
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(delayExchange()).with("test_queue").noargs();
	}
	

}
*/