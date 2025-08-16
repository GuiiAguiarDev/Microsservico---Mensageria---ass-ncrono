package com.bank.messasing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.bank.config.RabbitMQConfig;
import com.bank.dto.ClientRequestDTO;

@Service
public class ClientProducer {
	
	private final RabbitTemplate rabbittemplate;
	
	public ClientProducer(RabbitTemplate rabbittemplate) {
		this.rabbittemplate = rabbittemplate;
	}
	
	//Salvar
	public void save(ClientRequestDTO clientRequest) {
		rabbittemplate.convertAndSend(RabbitMQConfig.QUEUE_CLIENT_SAVE,clientRequest);
	}
	
	//Update
	public void update (ClientRequestDTO updateDTO) {
		rabbittemplate.convertAndSend(RabbitMQConfig.QUEUE_CLIENT_UPDATE, updateDTO);
	}

	
	//Delete
	public void delete(ClientRequestDTO deletDTO) {
		rabbittemplate.convertAndSend(RabbitMQConfig.QUEUE_CLIENT_DELETE, deletDTO);
		System.out.println("Delete set to queue" +deletDTO.getId() );
	}
}
