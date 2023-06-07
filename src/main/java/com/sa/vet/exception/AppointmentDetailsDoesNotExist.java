package com.sa.vet.exception;

public class AppointmentDetailsDoesNotExist extends Exception {

	public AppointmentDetailsDoesNotExist() {
		super();
	}

	public AppointmentDetailsDoesNotExist(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AppointmentDetailsDoesNotExist(String message, Throwable cause) {
		super(message, cause);
	}

	public AppointmentDetailsDoesNotExist(String message) {
		super(message);
	}

	public AppointmentDetailsDoesNotExist(Throwable cause) {
		super(cause);
	}
	
}
