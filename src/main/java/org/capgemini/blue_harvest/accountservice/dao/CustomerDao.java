package org.capgemini.blue_harvest.accountservice.dao;

import java.util.List;
import java.util.Optional;

import org.capgemini.blue_harvest.accountservice.entity.Customer;

public interface CustomerDao {
	public Optional<Customer> getCustomerById(long id);
	public List<Customer> getAllCustomers();

}
