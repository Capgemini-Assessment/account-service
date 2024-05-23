package org.capgemini.blue_harvest.accountservice.model;

import java.time.LocalDateTime;

public class Account {

	private long id;
	private long customerId;
	private LocalDateTime createdAt = LocalDateTime.now();

	public Account() {
	}

	public Account(long id, long customerId, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
