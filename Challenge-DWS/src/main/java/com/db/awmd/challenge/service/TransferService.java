package com.db.awmd.challenge.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.repository.AccountsRepository;

import lombok.Getter;

public class TransferService {
	 
	@Autowired
	  private AccountsRepository accountsRepository;
	 
	/* @Getter
	  private final AccountsRepository accountsRepository;
	  
	  
	  @Autowired
	  public TransferService(AccountsRepository accountsRepository) {
	    this.accountsRepository = accountsRepository;
}*/
  // this transferAmount method fetch data of accounts repository of transferAmout method
	  
   public void transferAmount(Account accountFrom, Account accountTo, BigDecimal amount) {
	   this.accountsRepository.transferAmount(accountFrom, accountTo, amount);
  }

public void transferAmount(Account toAccount) {
	// TODO Auto-generated method stub
	
}




}