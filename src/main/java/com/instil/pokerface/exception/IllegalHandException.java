package com.instil.pokerface.exception;

/**
 * An exception that can be thrown if a hand of cards is though to be in an illegal state
 */
public class IllegalHandException extends Exception {
	
	private static final long serialVersionUID = -878879056339561143L;

	public IllegalHandException(String errorMessage) {
		super(errorMessage);
	}

}
