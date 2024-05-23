package org.capgemini.blue_harvest.accountservice.service;

import java.util.List;

import org.capgemini.blue_harvest.accountservice.model.Account;
import org.capgemini.blue_harvest.accountservice.model.AccountRequest;

public interface AccountService {
	public Account openCurrentAccount(AccountRequest request);

	public List<Account> getAccountsByCustomerId(int customerId);

}
