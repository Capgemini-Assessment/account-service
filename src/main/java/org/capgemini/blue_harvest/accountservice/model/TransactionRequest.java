package org.capgemini.blue_harvest.accountservice.model;
import java.time.LocalDateTime;


public class TransactionRequest {
	private int accountId;
    private double amount;
    private String type;
    private LocalDateTime transactionDate;
    
    public TransactionRequest() {
    }

    public TransactionRequest(int accountId, double amount, String type, LocalDateTime transactionDate) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.type = type;
		this.transactionDate = transactionDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
    
