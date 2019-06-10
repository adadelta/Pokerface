package com.instil.pokerface.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.instil.pokerface.model.Card;
import com.instil.pokerface.model.Rank;
import com.instil.pokerface.model.Suit;

public class CardTest {
	
	@Test
	void compareTo_Card5S_ShouldBeOrderedBeforeAS() {
		Card card5S = new Card(Rank.FIVE, Suit.SPADES);
		Card cardAS = new Card(Rank.A, Suit.SPADES);
		Assertions.assertEquals(-9, card5S.compareTo(cardAS));
	}
	
	@Test
	void compareTo_Card5S_ShouldBeSameAs5S() {
		Card card5S = new Card(Rank.FIVE, Suit.SPADES);
		Card card5SOther = new Card(Rank.FIVE, Suit.SPADES);
		Assertions.assertEquals(0, card5S.compareTo(card5SOther));
	}
	
	@Test
	void compareTo_Card5S_ShouldBeAfter2S() {
		Card card5S = new Card(Rank.FIVE, Suit.SPADES);
		Card card2S = new Card(Rank.TWO, Suit.SPADES);
		Assertions.assertEquals(3, card5S.compareTo(card2S));
	}
	
	@Test
	void compareTo_Card7C_ShouldBeBefore7D() {
		Card card5S = new Card(Rank.SEVEN, Suit.CLUBS);
		Card card2S = new Card(Rank.SEVEN, Suit.DIAMONDS);
		Assertions.assertEquals(-1, card5S.compareTo(card2S));
	}
	
}
