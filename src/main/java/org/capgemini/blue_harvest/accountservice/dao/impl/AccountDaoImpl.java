package org.capgemini.blue_harvest.accountservice.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.capgemini.blue_harvest.accountservice.constant.AccountConstant;
import org.capgemini.blue_harvest.accountservice.dao.AccountDao;
import org.capgemini.blue_harvest.accountservice.entity.Account;
import org.capgemini.blue_harvest.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDaoImpl implements AccountDao {
    
    private static final Logger logger = Logger.getLogger(AccountDaoImpl.class.getName());
    
    @Autowired
    private AccountRepository repository;

    @Override
    public Account save(Account account) {
    	logger.log(Level.FINE,AccountConstant.SAVING_ACCOUNT_LOG_MESSAGE, account);
        return repository.save(account);
    }

    @Override
    public List<Account> getAccountsByCustomerId(int customerId) {
    	logger.log(Level.FINE,AccountConstant.FETCHING_ACCOUNTS_LOG_MESSAGE, customerId);
        List<Account> accounts = repository.findByCustomerId(customerId);
        logger.log(Level.FINE,AccountConstant.FETCHED_ACCOUNTS_LOG_MESSAGE,new Object[]{accounts.size(), customerId});
        return accounts;
    }

}
