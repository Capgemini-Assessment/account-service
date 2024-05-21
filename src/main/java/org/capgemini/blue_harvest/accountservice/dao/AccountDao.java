package org.capgemini.blue_harvest.accountservice.dao;

import java.util.List;

import org.capgemini.blue_harvest.accountservice.entity.Account;

public interface AccountDao {
	
	public Account save(Account account);

	public List<Account> getAccountsByCustomerId(int customerId);

}
