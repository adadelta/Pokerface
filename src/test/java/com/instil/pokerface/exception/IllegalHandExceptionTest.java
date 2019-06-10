package com.instil.pokerface.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.instil.pokerface.exception.IllegalHandException;

public class IllegalHandExceptionTest {
	
	private final String message = "Exception message";
	
	@Test
	void illegalCardException_message_shouldReturnMessage() {
		IllegalHandException exception = new IllegalHandException(message);
		Assertions.assertEquals(message, exception.getMessage());
	}

}
