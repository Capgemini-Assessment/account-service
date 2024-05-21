package org.capgemini.blue_harvest.accountservice.service.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.capgemini.blue_harvest.accountservice.constant.AccountConstant;
import org.capgemini.blue_harvest.accountservice.dao.AccountDao;
import org.capgemini.blue_harvest.accountservice.dao.CustomerDao;
import org.capgemini.blue_harvest.accountservice.entity.Account;
import org.capgemini.blue_harvest.accountservice.entity.Customer;
import org.capgemini.blue_harvest.accountservice.exception.CustomerNotFoundException;
import org.capgemini.blue_harvest.accountservice.mapper.AccountServiceMapper;
import org.capgemini.blue_harvest.accountservice.model.AccountRequest;
import org.capgemini.blue_harvest.accountservice.model.TransactionRequest;
import org.capgemini.blue_harvest.accountservice.model.TransactionResponse;
import org.capgemini.blue_harvest.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public org.capgemini.blue_harvest.accountservice.model.Account openCurrentAccount(AccountRequest accountRequest) {
        logger.info(AccountConstant.LOG_RECEIVED_OPEN_ACCOUNT_REQUEST + accountRequest.getCustomerId());

        Customer customer = customerDao.getCustomerById(accountRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(AccountConstant.CUSTOMER_NOT_FOUND_MESSAGE + accountRequest.getCustomerId()));

        Account account = createAccountForCustomer(customer);

        if (accountRequest.getIntialCredit() > 0) {
            createInitialTransaction(account, accountRequest.getIntialCredit());
        }

        logger.info(AccountConstant.LOG_ACCOUNT_OPENED_SUCCESS + accountRequest.getCustomerId());
        return new AccountServiceMapper().mapToModel(account);
    }

    private Account createAccountForCustomer(Customer customer) {
        logger.info(AccountConstant.LOG_CREATING_ACCOUNT + customer.getId());

        Account account = new Account();
        account.setCustomer(customer);
        account.setCreatedAt(LocalDateTime.now());
        accountDao.save(account);

        logger.info(AccountConstant.LOG_ACCOUNT_CREATED + account.getId() + " for customer ID: " + customer.getId());
        return account;
    }

    private void createInitialTransaction(Account account, double initialCredit) {
        logger.info(AccountConstant.LOG_CREATING_TRANSACTION + account.getId());

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(account.getId());
        transactionRequest.setAmount(initialCredit);
        transactionRequest.setType(AccountConstant.CREDIT);
        transactionRequest.setTransactionDate(account.getCreatedAt());

        try {
            TransactionResponse response = webClientBuilder.build()
                    .post()
                    .uri(AccountConstant.TRANSACTION_SERVICE_URL)
                    .body(Mono.just(transactionRequest), TransactionRequest.class)
                    .retrieve()
                    .bodyToMono(TransactionResponse.class)
                    .block();
            logger.info(AccountConstant.LOG_TRANSACTION_CREATED + account.getId());
        } catch (WebClientResponseException e) {
        	logger.log(Level.SEVERE,AccountConstant.TRANSACTION_CREATION_ERROR_MESSAGE  + e.getMessage());
            throw new RuntimeException(AccountConstant.TRANSACTION_CREATION_ERROR_MESSAGE + e.getMessage(), e);
        }
    }

    @Override
    public List<org.capgemini.blue_harvest.accountservice.model.Account> getAccountsByCustomerId(Long customerId) {
        logger.info(AccountConstant.LOG_FETCHING_ACCOUNTS + customerId);

        Customer customer = customerDao.getCustomerById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(AccountConstant.CUSTOMER_NOT_FOUND_MESSAGE + customerId));

        List<Account> accounts = accountDao.getAccountsByCustomerId(customer.getId());
        AccountServiceMapper mapper = new AccountServiceMapper();

        List<org.capgemini.blue_harvest.accountservice.model.Account> result = accounts.stream()
                       .map(mapper::mapToModel)
                       .collect(Collectors.toList());

        logger.info(AccountConstant.LOG_ACCOUNTS_FETCHED + result.size() + " accounts for customer ID: " + customerId);
        return result;
    }
}