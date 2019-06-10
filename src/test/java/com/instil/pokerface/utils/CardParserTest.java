package com.instil.pokerface.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.instil.pokerface.exception.IllegalCardException;
import com.instil.pokerface.exception.IllegalHandException;
import com.instil.pokerface.model.Card;
import com.instil.pokerface.model.PokerHand;
import com.instil.pokerface.model.Rank;
import com.instil.pokerface.model.Suit;

public class CardParserTest {
	
	@Test
	void parseHand_parseLegal5CardHandString_ShouldReturnSameHandAsPokerHandObject() throws IllegalCardException, IllegalHandException {
		Card nineOfDiamonds = new Card(	Rank.NINE, 		Suit.DIAMONDS);
		Card jokerOfClubs = new Card(	Rank.J, 		Suit.CLUBS);
		Card nineOfSpades = new Card(	Rank.NINE, 		Suit.SPADES);
		Card fiveOfHearts = new Card(	Rank.FIVE, 		Suit.HEARTS);
		Card sevenOfClubs = new Card(	Rank.SEVEN, 	Suit.CLUBS);
		PokerHand hand = CardParser.parseHand("9D JC 9S 5H 7C");
		Assertions.assertAll(() -> Assertions.assertTrue(hand.containsCard(nineOfDiamonds)), 
							 () -> Assertions.assertTrue(hand.containsCard(jokerOfClubs)),
							 () -> Assertions.assertTrue(hand.containsCard(nineOfSpades)),
							 () -> Assertions.assertTrue(hand.containsCard(fiveOfHearts)),
							 () -> Assertions.assertTrue(hand.containsCard(sevenOfClubs)));
	}
	
	@Test
	void parseCard_parseLegalCardString_ShouldReturnCardObjectOfString() throws IllegalCardException {
		Card queenOfHearts = new Card(Rank.Q, Suit.HEARTS);
		Card parsedCard = CardParser.parseCard("QH");
		Assertions.assertTrue(queenOfHearts.equals(parsedCard));
	}
	

}
