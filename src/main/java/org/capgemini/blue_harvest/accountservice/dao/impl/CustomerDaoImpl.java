package org.capgemini.blue_harvest.accountservice.dao.impl;

import java.util.List;
import java.util.Optional;

import org.capgemini.blue_harvest.accountservice.dao.CustomerDao;
import org.capgemini.blue_harvest.accountservice.entity.Customer;
import org.capgemini.blue_harvest.accountservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	CustomerRepository repository;

	@Override
	public Optional<Customer> getCustomerById(long id) {
		return repository.findById(id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return repository.findAll();
	}

}
