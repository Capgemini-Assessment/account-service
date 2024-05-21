package org.capgemini.blue_harvest.accountservice.service;

import org.capgemini.blue_harvest.accountservice.model.Account;
import org.capgemini.blue_harvest.accountservice.model.AccountRequest;

public interface AccountService {
	public Account openCurrentAccount(AccountRequest request);

}
