package org.capgemini.blue_harvest.accountservice.model;

public class AccountRequest {
	private int customerId;
	private double intialCredit;

	public AccountRequest(int customerId, double intialCredit) {
		super();
		this.customerId = customerId;
		this.intialCredit = intialCredit;
	}

	public AccountRequest() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getIntialCredit() {
		return intialCredit;
	}

	public void setIntialCredit(double intialCredit) {
		this.intialCredit = intialCredit;
	}

}
