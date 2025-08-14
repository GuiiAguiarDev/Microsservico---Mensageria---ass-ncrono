package com.bank.service;

import org.springframework.stereotype.Service;

import com.bank.dto.ClientCurrentAccountRequestDTO;
import com.bank.messasing.CurrentAccountProducer;

@Service
public class CurrentAccountService {

	private final CurrentAccountProducer producer;

	public CurrentAccountService(CurrentAccountProducer producer) {

		this.producer = producer;
	}

	public void createAccountAsync(ClientCurrentAccountRequestDTO request) {
		producer.send(request);
	}
}
