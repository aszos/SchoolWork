/*
	Created by Alexander Szostek
	Created on February 24th, 2014
	CISC 181, Lab Section 041
	Practice Set #2
*/

public class Account 
{
	private String customerName;
	private double customerBalance;
	private double interestRate;
	private double balanceLimit = 0;
	
	public Account(String customerName, double customerBalance, double interestRate, double balanceLimit)
	{
		this.customerName = customerName;
		this.customerBalance = customerBalance;
		this.interestRate = interestRate;
		this.balanceLimit = balanceLimit;
	}
	
	public Account(String customerName, double customerBalance, double interestRate)
	{
		this(customerName, customerBalance, interestRate, 0);
	}
	
	public double getBalance()
	{
		return this.customerBalance;
	}
	
	public void depositQuantity(double quantity)
	{
		this.customerBalance += Math.abs(quantity); 
	}
	
	public boolean withdrawQuantity(double quantity)
	{
		if((this.customerBalance - Math.abs(quantity)) >= this.balanceLimit)
		{
			this.customerBalance -= Math.abs(quantity);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addMonthlyInterest()
	{
		this.customerBalance *= 1 + Math.abs(interestRate);
	}
	
	public void payoffBalance(Account accountToPay)
	{
		if(this.customerBalance > 0 && accountToPay.getBalance() < 0)
		{
			if(this.customerBalance > Math.abs(accountToPay.getBalance()))
			{
				double debtAbleToPay = this.customerBalance - Math.abs(accountToPay.getBalance());
				this.customerBalance -= debtAbleToPay;
				accountToPay.depositQuantity(debtAbleToPay);
			}
			else
			{
				accountToPay.depositQuantity(this.customerBalance);
				this.customerBalance = 0;
			}
		}	
	}
}
