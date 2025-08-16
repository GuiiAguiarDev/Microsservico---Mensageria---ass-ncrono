package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.dto.ClientRequestDTO;
import com.bank.exception.ResourceNotFoundException;
import com.bank.messasing.ClientProducer;
import com.bank.model.Client;
import com.bank.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientProducer clientProducer;
	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository, ClientProducer clientProducer) {
		this.clientProducer = clientProducer;
		this.clientRepository = clientRepository;
	}

	// Salvar - Rabbit
	public void save(ClientRequestDTO dto) {
		clientProducer.save(dto);
	}

	// Não passa pelo rabbit

	public List<Client> list() {
		return clientRepository.findAll();

	}

	// Não passa pelo rabbit
	public Client findById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));

	}

	// Update - Rabbit

	public void updateClient(ClientRequestDTO dto) {

		clientProducer.update(dto);
	}

	public void delete(ClientRequestDTO dto) {
		clientProducer.delete(dto);
	}

	
}
