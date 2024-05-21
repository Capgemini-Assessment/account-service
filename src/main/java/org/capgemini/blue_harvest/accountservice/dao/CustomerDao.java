package org.capgemini.blue_harvest.accountservice.dao;

import java.util.Optional;

import org.capgemini.blue_harvest.accountservice.entity.Customer;

public interface CustomerDao {
	public Optional<Customer> getCustomerById(long id);

}
