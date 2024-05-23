package org.capgemini.blue_harvest.accountservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.capgemini.blue_harvest.accountservice.dao.AccountDao;
import org.capgemini.blue_harvest.accountservice.dao.CustomerDao;
import org.capgemini.blue_harvest.accountservice.entity.Account;
import org.capgemini.blue_harvest.accountservice.entity.Customer;
import org.capgemini.blue_harvest.accountservice.exception.CustomerNotFoundException;
import org.capgemini.blue_harvest.accountservice.exception.InvalidAmountException;
import org.capgemini.blue_harvest.accountservice.mapper.AccountServiceMapper;
import org.capgemini.blue_harvest.accountservice.model.AccountRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

	@Mock
	private CustomerDao customerDao;

	@Mock
	private AccountDao accountDao;

	@Autowired
	private WebTestClient webTestClient;

	@Mock
	private AccountServiceMapper accountServiceMapper;

	@Mock
	private Logger logger;

	@Mock
	private WebClient.Builder webClientBuilder;

	@InjectMocks
	private AccountServiceImpl accountService;

	@Test
	void testGetAccountsByCustomerId() {
		int customerId = 1;

		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john.doe@example.com");

		when(customerDao.getCustomerById(customerId)).thenReturn(Optional.of(customer));

		List<Account> accounts = new ArrayList<>();
		Account account1 = new Account();
		account1.setId(1);
		account1.setCustomer(customer);
		account1.setCreatedAt(LocalDateTime.now());
		accounts.add(account1);

		when(accountDao.getAccountsByCustomerId(customerId)).thenReturn(accounts);

		List<org.capgemini.blue_harvest.accountservice.model.Account> result = accountService
				.getAccountsByCustomerId(customerId);

		assertEquals(accounts.size(), result.size());
	}

	@Test
	public void testGetAccountsByCustomerId_CustomerNotFound() {
		int customerId = 1;

		when(customerDao.getCustomerById(customerId)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class, () -> {
			accountService.getAccountsByCustomerId(customerId);
		});
		verify(accountDao, never()).getAccountsByCustomerId(customerId);
	}

	@Test
	void testOpenCurrentAccountWithNegativeInitialCredit() {
		MockitoAnnotations.openMocks(this);
		AccountRequest accountRequest = new AccountRequest();
		accountRequest.setCustomerId(1);
		accountRequest.setIntialCredit(-100);

		assertThrows(InvalidAmountException.class, () -> {
			accountService.openCurrentAccount(accountRequest);
		});

	}

	@Test
	void testOpenCurrentAccountWithValidAmountAndCustomerNotFound() {
		MockitoAnnotations.openMocks(this);

		int customerId = 1;
		double initialCredit = 100;
		AccountRequest accountRequest = new AccountRequest(customerId, initialCredit);

		when(customerDao.getCustomerById(customerId)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class, () -> {
			accountService.openCurrentAccount(accountRequest);
		});
	}

	@Test
	void testOpenCurrentAccountWithZeroInitialCreditAndExistingCustomer() {
		int customerId = 1;
		double initialCredit = 0;
		AccountRequest accountRequest = new AccountRequest();
		accountRequest.setCustomerId(customerId);
		accountRequest.setIntialCredit(initialCredit);

		Customer customer = new Customer();
		customer.setId(customerId);

		Account account = new Account();
		account.setId(1);
		account.setCustomer(customer);
		account.setCreatedAt(LocalDateTime.now());

		when(customerDao.getCustomerById(customerId)).thenReturn(Optional.of(customer));

		org.capgemini.blue_harvest.accountservice.model.Account result = accountService
				.openCurrentAccount(accountRequest);

		verify(customerDao, times(1)).getCustomerById(customerId);
		Assertions.assertNotNull(result);
	}
}