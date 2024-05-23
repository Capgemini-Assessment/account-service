package org.capgemini.blue_harvest.accountservice.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.capgemini.blue_harvest.accountservice.entity.Customer;
import org.capgemini.blue_harvest.accountservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerDaoImplTest {

	@Mock
	private CustomerRepository repository;

	@InjectMocks
	private CustomerDaoImpl customerDao;

	@Test
	void testGetCustomerById() {
		int id = 1;
		Customer customer = new Customer();
		customer.setId(id);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john.doe@example.com");

		when(repository.findById(id)).thenReturn(Optional.of(customer));

		Optional<Customer> retrievedCustomer = customerDao.getCustomerById(id);
		assertEquals(customer, retrievedCustomer.orElse(null));
	}

	@Test
	void testGetAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setFirstName("John");
		customer1.setLastName("Doe");
		customer1.setEmail("john.doe@example.com");
		customers.add(customer1);

		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setFirstName("Jane");
		customer2.setLastName("Smith");
		customer2.setEmail("jane.smith@example.com");
		customers.add(customer2);

		when(repository.findAll()).thenReturn(customers);

		List<Customer> retrievedCustomers = customerDao.getAllCustomers();
		assertEquals(customers.size(), retrievedCustomers.size());
	}
}
