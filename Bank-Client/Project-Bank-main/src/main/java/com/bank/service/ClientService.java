package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.exception.ResourceNotFoundException;
import com.bank.model.Client;
import com.bank.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;


	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	
	}

	public Client save(Client client) {
		return clientRepository.save(client);

	}

	public List<Client> list() {
		return clientRepository.findAll();

	}

	public Client findById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));

	}

	public Client updateClient(Long id, Client updatedClient) {
		Client existingUser = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid update - Client not found with id: " + id));


		existingUser.setName(updatedClient.getName());
		existingUser.setAge(updatedClient.getAge());
		existingUser.setEmail(updatedClient.getEmail());
		existingUser.setAddress(updatedClient.getAddress());
		existingUser.setSalary(updatedClient.getSalary());

		

		return clientRepository.save(existingUser);

	}

	public void deleteClient(Long id) {

		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid delete - Client not found with id: " + id));

		clientRepository.delete(client);

	}
}
