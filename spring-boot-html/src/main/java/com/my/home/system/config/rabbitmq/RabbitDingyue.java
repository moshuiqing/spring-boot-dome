package com.my.home.system.config.rabbitmq;


import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDingyue {
	
	
	/**
	 * @return
	 * 1创建一个路由
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanout-exchange");
		
	}
	
	/**
	 * @return
	 * 1队列
	 */
	@Bean
	public Queue queueone() {
		return new AnonymousQueue();
		
	}
	/**
	 * @return
	 * 1队列
	 */
	@Bean
	public Queue queuetwo() {
		return new AnonymousQueue();
		
	}
	/**
	 * @return
	 * 1队列
	 */
	@Bean
	public Queue queuethree() {
		return new AnonymousQueue();
		
	}
	
	/**
	 * @param exchange
	 * @param autoQueue
	 * @return
	 * 1绑定
	 */
	@Bean
	public Binding bindingone(FanoutExchange exchange,Queue queueone) {
		return BindingBuilder.bind(queueone).to(exchange);		
	}
	/**
	 * @param exchange
	 * @param autoQueue
	 * @return
	 * 1绑定
	 */
	@Bean
	public Binding bindingtwo(FanoutExchange exchange,Queue queuetwo) {
		return BindingBuilder.bind(queuetwo).to(exchange);		
	}
	/**
	 * @param exchange
	 * @param autoQueue
	 * @return
	 * 1绑定
	 */
	@Bean
	public Binding bindingthree(FanoutExchange exchange,Queue queuethree) {
		return BindingBuilder.bind(queuethree).to(exchange);		
	}

}
