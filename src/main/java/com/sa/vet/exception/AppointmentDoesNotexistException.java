package com.sa.vet.exception;

public class AppointmentDoesNotexistException extends Exception {

	public AppointmentDoesNotexistException() {
		super();
	}

	public AppointmentDoesNotexistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AppointmentDoesNotexistException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppointmentDoesNotexistException(String message) {
		super(message);
	}

	public AppointmentDoesNotexistException(Throwable cause) {
		super(cause);
	}
	
}
