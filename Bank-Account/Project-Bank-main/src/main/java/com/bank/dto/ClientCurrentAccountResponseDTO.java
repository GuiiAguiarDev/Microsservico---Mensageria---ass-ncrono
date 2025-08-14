package com.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClientCurrentAccountResponseDTO {
	/*DTO que vai auxiliar na busca dos dados e tazer tudo amarrado do client
	com a conta corrente, quando a gente fazer o GetMapping por id ou geral
	Explicando: vai trazer os dados da conta que vai ate o createDate e depois
	todos os dados do Client, depois de fazer a DTO eu termino de passar 
	os dados na service e controler
	*/
	
	private String number;
	private BigDecimal balance;
	private LocalDate createDate;
	//Dados que vem da DTO CLIENT que definimos, vem todos os dados de lá
	private ClientDTO client;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = client;
	}
	
	


}
