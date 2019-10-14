package com.my.home.system.comment.rabbitmq;
/*package com.my.home.system.comment;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpComment {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	//生产者
	public void send(String msg) {
		this.amqpTemplate.convertAndSend("roncoo.queue",msg);
	}
	
	//消费者
	@RabbitListener(queues="roncoo.queue")
	public void receiveQueue(String text) {
		System.out.println("接收到："+text);
	}

}
*/