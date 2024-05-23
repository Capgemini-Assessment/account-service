package org.capgemini.blue_harvest.accountservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.capgemini.blue_harvest.accountservice.model.Customer;

public class CustomerServiceMapper {

	public List<Customer> mapToModel(List<org.capgemini.blue_harvest.accountservice.entity.Customer> entityList) {
		List<Customer> outputList = new ArrayList<Customer>();
		entityList.forEach(entity -> {
			Customer customer = new Customer();
			customer.setFirstName(entity.getFirstName());
			customer.setLastName(entity.getLastName());
			customer.setEmail(entity.getEmail());
			customer.setId(entity.getId());
			customer.setCreatedAt(entity.getCreatedAt());
			outputList.add(customer);
		});
		return outputList;
	}
}
