package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
	Customermessage mes1;
	

	
	
	@RabbitListener(queues=MQconfig.que)
	public void listerner1(Customermessage mes) {
		
		System.out.println(mes.toString());
	
		
		
	}
	
}
