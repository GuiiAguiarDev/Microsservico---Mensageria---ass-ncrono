package com.bank.messasing;

import java.awt.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.bank.config.RabbitMQConfig;
import com.bank.dto.ClientRequestDTO;
import com.bank.exception.ResourceNotFoundException;
import com.bank.model.Client;
import com.bank.repository.ClientRepository;

@Service
public class ClientConsumer {

	private final ClientRepository clientRepository;

	public ClientConsumer(ClientRepository clientRepository) {

		this.clientRepository = clientRepository;
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT_DELETE)
	public void delete(ClientRequestDTO dto) {
		Client client = clientRepository.findById(dto.getId()).orElseThrow(()-> new ResourceNotFoundException("Client not found"));
		clientRepository.delete(client);
		System.out.println("Sucess Deleted RabbitMQ");
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT_UPDATE)
	public void update(ClientRequestDTO updateDTO) {
	Client client = clientRepository.findById(updateDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
	client.setName(updateDTO.getName());
	client.setAge(updateDTO.getAge());
	client.setAddress(updateDTO.getAddress());
	client.setEmail(updateDTO.getEmail());
	client.setSalary(updateDTO.getSalary());
	
	clientRepository.save(client);
	System.out.println("Sucess Update RabbitMQ!");
	
	
	}

	//Salvar
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
