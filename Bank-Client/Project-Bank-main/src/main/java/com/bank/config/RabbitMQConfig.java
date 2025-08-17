package com.bank.config;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	//Criação das constante com o nome das filas
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
	
	
	//Criação das constante com o nome das filas de descarte de possiveis erros
	public static final String DLQ_CLIENT_DELETE = "client-dql-delete";
	public static final String DLQ_CLIENT_UPDATE = "client-dql-update";
	
	@Bean
	public Queue clientDLQDDelete() {
		return new Queue(DLQ_CLIENT_DELETE, true);
	}
	
	@Bean
	public Queue clientDLQUpdate() {
		return new Queue(DLQ_CLIENT_UPDATE,true);
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
