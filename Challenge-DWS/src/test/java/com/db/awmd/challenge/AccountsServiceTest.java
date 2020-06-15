package com.db.awmd.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.exception.InSufficientBalanceException;
import com.db.awmd.challenge.repository.AccountsRepositoryInMemory;
import com.db.awmd.challenge.service.AccountsService;
import com.db.awmd.challenge.service.TransferService;
import com.db.awmd.challenge.web.TransferController;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsServiceTest {

  @Autowired
  private AccountsService accountsService;
  
@Mock
private AccountsRepositoryInMemory  accountRepoMemo;

@Mock
private TransferService transferService;


  @Test
  public void addAccount() throws Exception {
    Account account = new Account("Id-123");
    account.setBalance(new BigDecimal(1000));
    this.accountsService.createAccount(account);

    assertThat(this.accountsService.getAccount("Id-123")).isEqualTo(account);
  }

  @Test
  public void addAccount_failsOnDuplicateId() throws Exception {
    String uniqueId = "Id-" + System.currentTimeMillis();
    Account account = new Account(uniqueId);
    this.accountsService.createAccount(account);

    try {
      this.accountsService.createAccount(account);
      fail("Should have failed when adding duplicate account");
    } catch (DuplicateAccountIdException ex) {
      assertThat(ex.getMessage()).isEqualTo("Account id " + uniqueId + " already exists!");
    }


 /*   @Test
    public <MoneyTransfer> void shouldCreateMoneyTransfer() {

      when(accountRepoMemo.save(any(TransferController.class))).thenAnswer(invocation -> {
        ((MoneyTransfer)invocation.getArguments()[0]).setId(transferService.transferMAmount(Id);
        return null;
      });

      MoneyTransfer moneyTransfer = .createMoneyTransfer(TransferInfo);

      verify(accountRepoMemo).save(moneyTransfer);
  
    	   
            @Test
    		public void getTransfertTest() throws ResourceNotFoundException {
    			Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));
    			assertNotNull(transferService.getTransferAmountByCustomerId(1l));
    		}
    	

	@Test
	public void getTransferListTest() throws Exception {
		Mockito.when(accountRepoMemo.findById(1l)).thenReturn(Optional.of(account));
		assertNotNull(transferService.getTransactionHistoryByCustomerId(1l));
	}*/

    @Test
	public void successfulTransfer() throws Exception {
		String uniqueId = "Id-" + System.currentTimeMillis();
		Account fromAccount = new Account(uniqueId);
		fromAccount.setBalance(BigDecimal.valueOf(10000));
		this. transferService.transferAmount(fromAccount, fromAccount, null);

		uniqueId = "Id-1" + System.currentTimeMillis();
		Account toAccount = new Account(uniqueId);
		toAccount.setBalance(BigDecimal.valueOf(10000));
		this.transferService.transferAmount(toAccount);

		String result = this.transferService.transferAmount(fromAccount.getAccountId(), toAccount.getAccountId(),
				BigDecimal.valueOf(100));
		assertEquals("Transfer Successful", result);

	}

	@Test
	public void insufficientBalance() throws Exception {
		String uniqueId = "Id-" + System.currentTimeMillis();
		Account fromAccount = new Account(uniqueId);
		fromAccount.setBalance(BigDecimal.valueOf(100));
		this.accountsService.createAccount(fromAccount);

		uniqueId = "Id-1" + System.currentTimeMillis();
		Account toAccount = new Account(uniqueId);
		toAccount.setBalance(BigDecimal.valueOf(100));
		this.accountsService.createAccount(toAccount);
		try {
			String result = this.transferService.transferAmount(fromAccount.getAccountId(), toAccount.getAccountId(),
					BigDecimal.valueOf(100));
		} catch (Exception e) {
			assertEquals("Insufficient Balance", e.getMessage());
		}

    	
    
  }
}
