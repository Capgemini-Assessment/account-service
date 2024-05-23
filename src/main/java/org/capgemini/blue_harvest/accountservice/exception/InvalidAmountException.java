package org.capgemini.blue_harvest.accountservice.exception;

@SuppressWarnings("serial")
public class InvalidAmountException extends RuntimeException {

	public InvalidAmountException() {
		super();
	}

	public InvalidAmountException(String message) {
		super(message);
	}

	public InvalidAmountException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAmountException(Throwable cause) {
		super(cause);
	}
}
