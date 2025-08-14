package com.bank.messasing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.bank.config.RabbitMQConfig;
import com.bank.dto.ClientCurrentAccountRequestDTO;

@Service
public class CurrentAccountProducer {
	
	private final RabbitTemplate rabbittemplate;
	
	public CurrentAccountProducer(RabbitTemplate rabbittemplate) {
		this.rabbittemplate = rabbittemplate;
	}
	
	public void send(ClientCurrentAccountRequestDTO accountRequest) {
		rabbittemplate.convertAndSend(RabbitMQConfig.QUEUE_CURRENT_ACCOUNT,accountRequest);
	}

}
