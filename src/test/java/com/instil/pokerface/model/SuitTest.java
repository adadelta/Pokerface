package com.instil.pokerface.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.instil.pokerface.exception.IllegalCardException;
import com.instil.pokerface.model.Suit;


public class SuitTest {
	
	@Test
	void getSuitCharacter_HeartsChar_ShouldReturnCharH() {
		Assertions.assertEquals(Character.valueOf('H'), Suit.HEARTS.getSuitCharacter());
	}
	
	@Test
	void getSuitFromChar_CharH_ShouldReturnSuitHearts() throws IllegalCardException {
		Assertions.assertEquals(Suit.HEARTS, Suit.getSuitByCharacter('H'));
	}
	
	@Test
	void getSuitFromChar_CharZ_ShouldThrowIllegalCardException() {
		Assertions.assertThrows(IllegalCardException.class, () -> {
			Suit.getSuitByCharacter('Z');
		});
	}
	
}
