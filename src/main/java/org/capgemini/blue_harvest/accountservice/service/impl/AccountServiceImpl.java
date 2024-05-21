package org.capgemini.blue_harvest.accountservice.service.impl;


import java.time.LocalDateTime;
import java.util.Optional;

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

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
    private WebClient.Builder webClientBuilder;
	


	
	@Override
	@Transactional
	public org.capgemini.blue_harvest.accountservice.model.Account openCurrentAccount(AccountRequest accountRequest) {
        Customer customer = customerDao.getCustomerById(accountRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + accountRequest.getCustomerId() + " not found"));

        Account account = createAccountForCustomer(customer);

        if (accountRequest.getIntialCredit() > 0) {
            createInitialTransaction(account, accountRequest.getIntialCredit());
        }

        return new AccountServiceMapper().mapToModel(account);
    }

    private Account createAccountForCustomer(Customer customer) {
        Account account = new Account();
        account.setCustomer(customer);
        account.setCreatedAt(LocalDateTime.now());
        accountDao.save(account);
        return account;
    }

    private void createInitialTransaction(Account account, double initialCredit) {
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
        } catch (WebClientResponseException e) {
            // Replace with your logging framework's logger
            System.err.println("Error creating transaction: " + e.getMessage());
        }
    }

}
