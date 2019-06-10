package com.instil.pokerface.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.instil.pokerface.exception.IllegalCardException;
import com.instil.pokerface.model.Rank;

public class RankTest {

	@Test
	void getRankChararacter_RankAChar_ShouldReturnCharA() {
		Assertions.assertEquals(Character.valueOf('A'), Rank.A.getRankCharacter());
	}
	
	@Test
	void getRankByCharacter_CharA_ShouldReturnRankA() throws IllegalCardException {
		Assertions.assertEquals(Rank.A, Rank.getRankByCharacter('A'));
	}
	
	@Test
	void getRankByCharacter_CharZ_ShouldThrowIllegalCardException() {
		Assertions.assertThrows(IllegalCardException.class, () -> {
			Rank.getRankByCharacter('Z');
		});
	}

}
