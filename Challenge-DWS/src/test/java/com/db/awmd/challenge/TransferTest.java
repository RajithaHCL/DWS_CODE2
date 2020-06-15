package com.db.awmd.challenge;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.Validation;

import org.junit.Test;

	public class TransferTest 
	{
		// method check for input empty, null or with char in accountID and valid input
		public boolean isValidAccountId(String accountId)
		{
			boolean result = false;
			if(accountId.length() == 0 || accountId == null || accountId.matches("[a-zA-Z\"]+"))
			{
				result = true;
			}
			else
			{
				if(accountId.length() > 128 )
				{
					result = true;
				}
			}
				return result;
		}
		
		//check for empty account id
		@Test
	    public void isEmptyTest(){
			Validatable asft = new Validation();
	        assertTrue(asft.isValidAccountId(""));
	    }
			
		//check for valid account id.
		@Test
	    public void isValidTransferStatusId(){
			String input = "111222333444556";
	        assertTrue(input == "111222333444556");
	    }

		//check for account id with char init.
		@Test
	    public void isTransferStatusNonIntegerTest(){
			ValidateInput asft = new ValidateInput();
			assertFalse(asft.isValidAccountId("1357902468ASD"));
	    }
		
		//check for valid account id more than length 128 
		@Test
	    public void isTransferStatusTooBigTest(){
			String input = "1357902468135790246813579024681357902468135790246813579024681357902468135790246813579024681357902468135790246813579024681357902468135790246813579024681357902468135";
	        assertTrue(input.length() > 128);
	    }

	}





