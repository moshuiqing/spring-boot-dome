package com.my.home.system.comment.rabbitmq;
/*package com.my.home.system.comment;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.home.system.config.RabbitConfigure;
import com.my.home.system.po.BookUser;


//@RabbitListener除了可以作用在方法，也可以作用在类上。在后者的情况下，需要在处理的方法使用@RabbitHandler。一个类可以配置多个@RabbitHandler
//@RabbitListener(queues = RabbitMsgConvertConfigure.SPRING_BOOT_QUEUE)
@Component
public class ReceiveMsgComment {
	
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	//生产者
	public void send(String msg) {
		this.amqpTemplate.convertAndSend(RabbitConfigure.SPRING_BOOT_QUEUE,msg);
	}
	
	//发送对象
	public void sendMsgContent(BookUser user) {
		this.amqpTemplate.convertAndSend(RabbitConfigure.SPRING_BOOT_EXCHANGE,RabbitConfigure.SPRING_BOOT_BIND_KEY,user);
	}
	
	
	
	
	
	@RabbitListener(queues=RabbitConfigure.SPRING_BOOT_QUEUE)
	public void receive(String content) {
		System.out.println("msg:"+content);
	}
	
	@RabbitListener(queues=RabbitConfigure.SPRING_BOOT_QUEUE)
	public void msgContent(BookUser bookUser) {
		System.out.println("msgobj:"+bookUser.getUser());
	}
	
	
}
*/