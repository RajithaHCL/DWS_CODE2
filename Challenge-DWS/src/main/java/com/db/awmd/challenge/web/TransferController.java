package com.db.awmd.challenge.web;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.InSufficientBalanceException;
import com.db.awmd.challenge.service.TransferService;

public class TransferController {
	
	@Autowired
	  private TransferService tansferService; 
	
	
	
	  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="/{accountTo}/{amount}")
	  public ResponseEntity<String> transferAmount(@RequestBody @Valid Account accountFrom, @PathVariable Account accountTo,
			  @PathVariable BigDecimal amount) {
		  try {
			  this.tansferService.transferAmount(accountFrom, accountTo, amount);
		  } catch(InSufficientBalanceException e){
			  return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		  }
		  return new ResponseEntity<>(HttpStatus.ACCEPTED);
	  }
/*
	  @PostMapping("/transaction/summary")
		public ResponseEntity<Object> getAllTransaction(@RequestBody AccountTransactionDto accountTransactionDto) throws ResourceNotFoundException
		{
			return new ResponseEntity<>(transactionService.getAllTransaction(accountTransactionDto.getCustomerId(), accountTransactionDto.getFromDate(), accountTransactionDto.getToDate()),HttpStatus.OK);
		}*/
}
