package com.bank.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.bank.model.CurrentAccount;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {


}
