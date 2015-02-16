/*
	Created by Alexander Szostek
	Created on February 24th, 2014
	CISC 181, Lab Section 041
	Practice Set #2
*/

import junit.framework.TestCase;

public class TestAccount extends TestCase 
{
	public void test_series() 
	{
		Account testAccount = new Account("John Doe", 0, 0.005);
		testAccount.depositQuantity(50);
		testAccount.withdrawQuantity(10);
		testAccount.addMonthlyInterest();
		assertEquals(40.2, testAccount.getBalance(), 0.01);
	}
	
	public void testDeposit()
	{
		Account testAccount = new Account("Alex Szostek", 1000, 0.001);
		
		double postTestBalance = 2000;
		
		testAccount.depositQuantity(1000);
		assertEquals(postTestBalance, testAccount.getBalance(), 0.001);		
	}
	
	public void testWithdraw()
	{
		Account testAccount = new Account("Alex Szostek", 1000, 0.001);
		double postTestBalance = 0;
		testAccount.withdrawQuantity(-1000);
		assertEquals(postTestBalance, testAccount.getBalance(), 0.001);	
		
		
		Account testCreditAccount = new Account("Alex Szostek", 500, 0.001, -100);
		double postCreditTestBalance = -50;
		testCreditAccount.withdrawQuantity(550);
		assertEquals(postCreditTestBalance, testCreditAccount.getBalance(), 0.001);
		
	}
	
	public void testMonthlyInterest()
	{
		Account testAccount = new Account("Alex Szostek", 1000, 0.001);
		
		double postTestBalance = 1001;
		
		testAccount.addMonthlyInterest();
		assertEquals(postTestBalance, testAccount.getBalance(), 0.001);
	}

	public void test_payoffDebt() {
	    Account positive = new Account("John Doe", 500.0, 0);
	    Account negative = new Account("John Doe", -250.0, 0.01, -5000);
	    
	    // won't do anything since we can't use a negative balance account
	    //  to payoff a positive balance account
	    negative.payoffBalance(positive); 
	    assertEquals(-250.0, negative.getBalance());
        assertEquals(500.0, positive.getBalance());
        
        // this will pay off the entire negative balance
	    positive.payoffBalance(negative);
        assertEquals(0.0, negative.getBalance());
        assertEquals(250.0, positive.getBalance());

        Account otherNegative = new Account("John Doe", -1000.0, 0.01, -5000);
        
        // this will pay off as much as it can, but will still leave
        //  some negative balance because there is not enough money
        //  in the positive account
        positive.payoffBalance(otherNegative);
        
        assertEquals(-750.0, otherNegative.getBalance());
        assertEquals(0.0, positive.getBalance());
	}
}
