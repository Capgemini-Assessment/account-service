package org.capgemini.blue_harvest.accountservice.mapper;

import org.capgemini.blue_harvest.accountservice.model.Account;

public class AccountServiceMapper {
	
	public Account mapToModel(org.capgemini.blue_harvest.accountservice.entity.Account entity) {
		Account account = new Account();
		account.setId(entity.getId());
		account.setCustomerId(entity.getCustomer().getId());
		return account;
	}

}
