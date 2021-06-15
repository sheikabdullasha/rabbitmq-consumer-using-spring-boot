package com.example.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQconfig {
	public static final String que="que1";
	public static final String ex="ex1";
	public static final String route="route1";
	@Bean
	public Queue queue() {
		return new Queue(que);
		
	}
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(ex);
	}
	@Bean
	public Binding binding(Queue que,TopicExchange ex) {
		return BindingBuilder
				.bind(que)
				.to(ex)
				.with(route);
	}
	
	@Bean
	public MessageConverter msgconv() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory fac) {
		RabbitTemplate temp=new RabbitTemplate(fac);
		temp.setMessageConverter(msgconv());
		return temp;
	}

}
