package com.instil.pokerface.exception;

/**
 * An exception that can be thrown if a card is though to be illegal
 */
public class IllegalCardException extends Exception {
	
	private static final long serialVersionUID = 8204304156568060719L;

	public IllegalCardException(String errorMessage) {
		super(errorMessage);
	}

}
