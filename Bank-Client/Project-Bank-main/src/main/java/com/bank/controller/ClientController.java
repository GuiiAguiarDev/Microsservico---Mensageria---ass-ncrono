package com.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.ClientRequestDTO;
import com.bank.model.Client;

import com.bank.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")

public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody ClientRequestDTO dto) {
		clientService.save(dto);
		return ResponseEntity.ok("Client sent to queue");
	}

	@GetMapping
	public List<Client> list() {
		return clientService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Long id) {
		Client client = clientService.findById(id);
		return ResponseEntity.ok(client);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ClientRequestDTO dto) {
		dto.setId(id);
		clientService.updateClient(dto);
		return ResponseEntity.ok("Sent to Queue");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		ClientRequestDTO dto = new ClientRequestDTO();
		dto.setId(id);
		clientService.delete(dto);
		return ResponseEntity.ok("Sent to Queue");
	}

}
