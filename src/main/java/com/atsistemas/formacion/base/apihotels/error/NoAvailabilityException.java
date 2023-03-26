package com.atsistemas.formacion.base.apihotels.error;

public class NoAvailabilityException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NoAvailabilityException(String message) {
		super(message);
	}
	
}
