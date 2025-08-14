package com.bank.model;

import jakarta.persistence.Entity;

@Entity
public class CurrentAccount extends Account {

	//Só precisei passar o Id mesmo, por ele temos acesso a todos os demais dados
	//não preciso passar eles aqui
	private Long clientId;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
