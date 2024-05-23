package org.capgemini.blue_harvest.accountservice.dao.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.capgemini.blue_harvest.accountservice.entity.Account;
import org.capgemini.blue_harvest.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountDaoImplTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountDaoImpl accountDao;

    @Test
    void testSaveAccount() {
        Account account = new Account();
        account.setId(1);
        when(repository.save(account)).thenReturn(account);
        Account savedAccount = accountDao.save(account);
        assertEquals(account, savedAccount);
    }

    @Test
    void testGetAccountsByCustomerId() {
        int customerId = 1;
        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account();
        account1.setId(1);        
        accounts.add(account1);
        
        Account account2 = new Account();
        account2.setId(2);       
        accounts.add(account2);

        when(repository.findByCustomerId(customerId)).thenReturn(accounts);

        List<Account> retrievedAccounts = accountDao.getAccountsByCustomerId(customerId);
        assertEquals(accounts.size(), retrievedAccounts.size());
    }

}