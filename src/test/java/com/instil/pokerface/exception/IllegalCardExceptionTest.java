package com.instil.pokerface.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.instil.pokerface.exception.IllegalCardException;

public class IllegalCardExceptionTest {
	
	private final String message = "Exception message";
	
	@Test
	void illegalCardException_message_shouldReturnMessage() {
		IllegalCardException exception = new IllegalCardException(message);
		Assertions.assertEquals(message, exception.getMessage());
	}

}
