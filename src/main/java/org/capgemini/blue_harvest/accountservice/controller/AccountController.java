package org.capgemini.blue_harvest.accountservice.controller;

import org.capgemini.blue_harvest.accountservice.constant.APIConstants;
import org.capgemini.blue_harvest.accountservice.constant.AccountConstant;
import org.capgemini.blue_harvest.accountservice.model.Account;
import org.capgemini.blue_harvest.accountservice.model.AccountRequest;
import org.capgemini.blue_harvest.accountservice.model.ResponseTemplate;
import org.capgemini.blue_harvest.accountservice.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.API_VERSION_1 + APIConstants.API_ACCOUNT_SERVICE)
public class AccountController {

	@Autowired
	private AccountService accountService;

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@PostMapping(APIConstants.API_OPEN_ACCOUNT)
	public ResponseEntity<ResponseTemplate> openCurrentAccount(@RequestBody AccountRequest accountRequest) {
		logger.info(AccountConstant.LOG_ACCOUNT_OPEN_REQUEST + accountRequest.getCustomerId());
		ResponseEntity<ResponseTemplate> responseEntity = new ResponseEntity<>(
				new ResponseTemplate(AccountConstant.ACCOUNT_OPEN_SUCCESS_MESSAGE, HttpStatus.CREATED.value(),
						accountService.openCurrentAccount(accountRequest)),
				HttpStatus.CREATED);
		logger.info(AccountConstant.LOG_ACCOUNT_OPEN_SUCCESS + accountRequest.getCustomerId());
		return responseEntity;
	}

	@GetMapping(APIConstants.API_GET_CUSTOMER_ACCOUNTS)
	public ResponseEntity<ResponseTemplate> getCustomerAccounts(
			@RequestParam(APIConstants.REQUEST_PARAM_CUSTOMER_ID) int customerId) {
		logger.info(AccountConstant.ACCOUNT_FETCH_REQUEST_LOGGER + customerId);
		List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
		ResponseEntity<ResponseTemplate> responseEntity = new ResponseEntity<>(
				new ResponseTemplate(AccountConstant.ACCOUNT_FETCH_SUCCESS_MESSAGE, HttpStatus.OK.value(), accounts),
				HttpStatus.OK);
		logger.info(AccountConstant.FETCHED_ACCOUNTS_LOG_MESSAGE , new Object[] { accounts.size(), customerId });
		return responseEntity;
	}
}
