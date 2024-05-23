package org.capgemini.blue_harvest.accountservice.controller;

import org.capgemini.blue_harvest.accountservice.constant.APIConstants;
import org.capgemini.blue_harvest.accountservice.constant.AccountConstant;
import org.capgemini.blue_harvest.accountservice.model.ResponseTemplate;
import org.capgemini.blue_harvest.accountservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.API_VERSION_1 + APIConstants.API_CUSTOMER_SERVICE)
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping(APIConstants.API_GET_ALL)
	public ResponseEntity<ResponseTemplate> getAllCustomers() {
		logger.info(AccountConstant.FETCH_ALL_CUSTOMERS_MESSAGE);
		ResponseEntity<ResponseTemplate> responseEntity = new ResponseEntity<>(
				new ResponseTemplate(AccountConstant.CUSTOMERS_FETCH_SUCCESS_MESSAGE, HttpStatus.OK.value(),
						customerService.getAllCustomers()),
				HttpStatus.OK);
		return responseEntity;
	}

}
