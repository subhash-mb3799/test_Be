package com.sa.vet.exception;

public class ResourceAlreadyExistException extends Exception {

	public ResourceAlreadyExistException() {
		super();
	}

	public ResourceAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceAlreadyExistException(String message) {
		super(message);
	}

	public ResourceAlreadyExistException(Throwable cause) {
		super(cause);
	}
	
}
