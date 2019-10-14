package com.my.home.system.comment.rabbitmq;

/*import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMsgDisplayComment {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMsg(String queueName,String msg) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("消息发送时间："+format.format(new Date()));
		
		rabbitTemplate.convertAndSend("test_exchange", queueName,msg,new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setHeader("x-delay",8000);
				return message;
			}
		});
	}
	

	
	
}
*/