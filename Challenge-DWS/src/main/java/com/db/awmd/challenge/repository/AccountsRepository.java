package com.db.awmd.challenge.repository;

import java.math.BigDecimal;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

public interface AccountsRepository {

  void createAccount(Account account) throws DuplicateAccountIdException;

  Account getAccount(String accountId);

  void clearAccounts();
  
  //tansferAmount is declared method here , because need to provide the implementation for this method
  
  void transferAmount(Account accountFrom, Account accountTo, BigDecimal amount);
  
  
   

}