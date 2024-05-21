package org.capgemini.blue_harvest.accountservice.dao.impl;

import org.capgemini.blue_harvest.accountservice.dao.AccountDao;
import org.capgemini.blue_harvest.accountservice.entity.Account;
import org.capgemini.blue_harvest.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private AccountRepository repository;

	@Override
	public Account save(Account account) {
		return repository.save(account);
	}

}
