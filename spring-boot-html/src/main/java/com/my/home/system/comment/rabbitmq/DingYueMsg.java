package com.my.home.system.comment.rabbitmq;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DingYueMsg {
	
	@Autowired
	public RabbitTemplate rabbitTemplate;
	
	@Autowired
    private FanoutExchange fanoutExchange;
	
	
	@RabbitListener(queues="#{queueone.name}")
	public void receiver0(String str) {
		System.out.println("one:"+str);
	}
	@RabbitListener(queues="#{queuetwo.name}")
	public void receiver1(String str) {
		System.out.println("two:"+str);
	}
	@RabbitListener(queues="#{queuethree.name}")
	public void receiver2(String str) {
		System.out.println("three:"+str);
	}
	
	
	public void send(String str) {
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", str);
        }
    }

}
