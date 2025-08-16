package com.bank.config;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	public static final String QUEUE_CLIENT_SAVE = "client-queue-save";

	public static final String QUEUE_CLIENT_UPDATE = "client-queue-update";
	
	public static final String QUEUE_CLIENT_DELETE = "client-queue-delete";
	@Bean
	public Queue clientQueueSave() {
		return new Queue(QUEUE_CLIENT_SAVE, true);

	}
	
	@Bean
	public Queue clientQueueUpdate() {
		return new Queue(QUEUE_CLIENT_UPDATE, true);

	}
	
	@Bean
	public Queue clientQueuDelete() {
		return new Queue(QUEUE_CLIENT_DELETE, true);

	}
	
	//Conversão para Json para funcionar nas requisições com a mensageria
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	//Metodo para aplicar a conversão
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory ) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(jackson2JsonMessageConverter());
		return template;
	}
}
