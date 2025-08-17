package com.bank.messasing;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.bank.config.RabbitMQConfig;
import com.bank.dto.ClientRequestDTO;
import com.bank.model.Client;
import com.bank.repository.ClientRepository;

@Service
public class ClientConsumer {

	private final ClientRepository clientRepository;
	private final RabbitTemplate rabbitTemplate;

	public ClientConsumer(ClientRepository clientRepository, RabbitTemplate rabbitTemplate) {

		this.clientRepository = clientRepository;
		this.rabbitTemplate = rabbitTemplate;

	}

	// Delete - completo com tratamento de erro de loop infinity
	@RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT_DELETE)
	public void delete(ClientRequestDTO dto) {
		Client client = clientRepository.findById(dto.getId()).orElse(null);

		if (client != null) {
			clientRepository.delete(client);
			System.out.println("Success deleted RabbitMQ id: " + dto.getId());
		} else {
			rabbitTemplate.convertAndSend(RabbitMQConfig.DLQ_CLIENT_DELETE, dto);
			System.out.println("Client not found, sent to DLQ DELETE RabbitMQ id: " + dto.getId());
		}

	}

	// Update - completo com tratamento de erro de loop infinity
	@RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT_UPDATE)
	public void update(ClientRequestDTO updateDTO) {
		Client client = clientRepository.findById(updateDTO.getId()).orElse(null);

		if (client != null) {
			client.setName(updateDTO.getName());
			client.setAge(updateDTO.getAge());
			client.setAddress(updateDTO.getAddress());
			client.setEmail(updateDTO.getEmail());
			client.setSalary(updateDTO.getSalary());
			clientRepository.save(client);
			System.out.println("Success update RabbitMQ id: "+updateDTO.getId());
		} else {
			rabbitTemplate.convertAndSend(RabbitMQConfig.DLQ_CLIENT_UPDATE, updateDTO);
			System.out.println("Client not found, sent to DLQ UPDATE RabbitMQ id: " + updateDTO.getId());
		}

	}

	// Salvar
	@RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT_SAVE)
	public void save(ClientRequestDTO request) {
		Client client = new Client();
		client.setName(request.getName());
		client.setAge(request.getAge());
		client.setAddress(request.getAddress());
		client.setEmail(request.getEmail());
		client.setSalary(request.getSalary());

		clientRepository.save(client);
		System.out.println("Client Created!");
	}

}
