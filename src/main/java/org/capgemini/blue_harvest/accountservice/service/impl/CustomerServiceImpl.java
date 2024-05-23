package org.capgemini.blue_harvest.accountservice.service.impl;

import java.util.List;
import org.capgemini.blue_harvest.accountservice.constant.AccountConstant;
import org.capgemini.blue_harvest.accountservice.dao.CustomerDao;
import org.capgemini.blue_harvest.accountservice.mapper.CustomerServiceMapper;
import org.capgemini.blue_harvest.accountservice.model.Customer;
import org.capgemini.blue_harvest.accountservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> getAllCustomers() {
		logger.info(AccountConstant.FETCHING_ALL_CUSTOMER_MESSAGE);
		List<org.capgemini.blue_harvest.accountservice.entity.Customer> customerEntities = customerDao
				.getAllCustomers();
		logger.info(AccountConstant.FETCHED_ALL_CUSTOMER_MESSAGE, customerEntities.size());
		return new CustomerServiceMapper().mapToModel(customerEntities);
	}

}
