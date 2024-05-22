package org.capgemini.blue_harvest.accountservice.exception;

import org.capgemini.blue_harvest.accountservice.constant.AccountConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(AccountConstant.CUSTOMER_NOT_FOUND_ERROR_MESSAGE + e.getMessage());
    }

	@ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Object> handleInvalidAmountException(InvalidAmountException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AccountConstant.INVALID_AMOUNT_ERROR_MESSAGE + e.getMessage());
    }
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnknownException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(AccountConstant.UNEXPECTED_ERROR_MESSAGE + e.getMessage());
    }
    
}