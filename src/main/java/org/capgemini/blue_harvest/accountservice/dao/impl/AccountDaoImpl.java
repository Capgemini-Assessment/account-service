package org.capgemini.blue_harvest.accountservice.dao.impl;

import java.util.List;

import org.capgemini.blue_harvest.accountservice.constant.AccountConstant;
import org.capgemini.blue_harvest.accountservice.dao.AccountDao;
import org.capgemini.blue_harvest.accountservice.entity.Account;
import org.capgemini.blue_harvest.accountservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDaoImpl implements AccountDao {

	private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

	@Autowired
	private AccountRepository repository;

	@Override
	public Account save(Account account) {
		logger.info(AccountConstant.SAVING_ACCOUNT_LOG_MESSAGE, account);
		return repository.save(account);
	}

	@Override
	public List<Account> getAccountsByCustomerId(int customerId) {
		logger.info(AccountConstant.FETCHING_ACCOUNTS_LOG_MESSAGE, customerId);
		List<Account> accounts = repository.findByCustomerId(customerId);
		logger.info(AccountConstant.FETCHED_ACCOUNTS_LOG_MESSAGE, new Object[] { accounts.size(), customerId });
		return accounts;
	}

}
