package org.capgemini.blue_harvest.accountservice.controller;

import org.capgemini.blue_harvest.accountservice.constant.AccountConstant;
import org.capgemini.blue_harvest.accountservice.model.AccountRequest;
import org.capgemini.blue_harvest.accountservice.model.ResponseTemplate;
import org.capgemini.blue_harvest.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/open")
    public ResponseEntity<ResponseTemplate> openCurrentAccount(@RequestBody AccountRequest accountRequest) {
    	ResponseEntity<ResponseTemplate> responseEntity = new ResponseEntity<>(new ResponseTemplate(AccountConstant.ACCOUNT_OPEN_SUCCESS_MESSAGE, HttpStatus.CREATED.toString(), accountService.openCurrentAccount(accountRequest)), HttpStatus.CREATED);
        return responseEntity;
    }
}
