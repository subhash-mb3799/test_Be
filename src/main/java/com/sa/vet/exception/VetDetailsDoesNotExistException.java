package com.sa.vet.exception;

public class VetDetailsDoesNotExistException extends Exception {

	public VetDetailsDoesNotExistException() {
		super();
	}

	public VetDetailsDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VetDetailsDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public VetDetailsDoesNotExistException(String message) {
		super(message);
	}

	public VetDetailsDoesNotExistException(Throwable cause) {
		super(cause);
	}
	

}
