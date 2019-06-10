package com.instil.pokerface.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.instil.pokerface.exception.IllegalHandException;
import com.instil.pokerface.model.PokerHand.PokerHandName;

public class PokerHandTest {
	
	@Test
	void pokerHand_CreateFullHand() throws IllegalHandException {
		new PokerHand(listOfTestCards(PokerHandName.HIGH_CARD));
	}
	
	@Test
	void pokerHand_CreateHandWith6Cards_ShouldThrowIllegalHandException() {
		List<Card> cards = listOfTestCards(PokerHandName.HIGH_CARD);
		cards.add(new Card(Rank.J, Suit.DIAMONDS));
		Assertions.assertThrows(IllegalHandException.class, () -> {
			new PokerHand(cards);
		});
	}
	
	@Test
	void countCardsInHand_Has5Cards_ShouldReturn5() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.HIGH_CARD));
		Assertions.assertEquals(5, hand.countCardsInHand());
	}
	
	@Test
	void getMaxHandSize_Has5Cards_ShouldReturn5() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.HIGH_CARD));
		Assertions.assertEquals(5, hand.getMaxHandSize());
	}
	
	@Test
	void getCards_Add3CardsToHand_ShouldReturnSameThreeCards() throws IllegalHandException {
		List<Card> threeCards = listOfTestCards(PokerHandName.HIGH_CARD).subList(0, 3);
		PokerHand hand = new PokerHand(threeCards);
		Assertions.assertTrue(threeCards.containsAll(hand.getCards()) && hand.getCards().containsAll(threeCards));
	}
	
	@Test
	void containsCard_Add2CardsToHandOneIs9D_ShouldReturnTrueFor9D() throws IllegalHandException {
		Card D9 = new Card(Rank.NINE, Suit.DIAMONDS);
		Card H3 = new Card(Rank.THREE, Suit.HEARTS);
		PokerHand hand = new PokerHand(Arrays.asList(D9, H3));
		Assertions.assertTrue(hand.containsCard(D9));
	}
	
	@Test
	void addCardToHand_Add1ToHandWith2Cards_HandShouldHaveSameThreeCards() throws IllegalHandException {
		
		// Get sub lists for testing
		List<Card> twoCards = listOfTestCards(PokerHandName.HIGH_CARD).subList(0, 2);
		List<Card> threeCard = listOfTestCards(PokerHandName.HIGH_CARD).subList(0, 3);
		
		// Create test hand
		PokerHand hand = new PokerHand(twoCards);
		hand.addCardToHand(listOfTestCards(PokerHandName.HIGH_CARD).get(2));
		
		Assertions.assertTrue(threeCard.containsAll(hand.getCards()) && hand.getCards().containsAll(threeCard));
	}
	
	@Test
	void addCardToHand_Add1ToFullHand_ShouldThrowIllegalHandException() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.HIGH_CARD));
		Assertions.assertThrows(IllegalHandException.class, () -> {
			hand.addCardToHand(new Card(Rank.FOUR, Suit.DIAMONDS));
		});
	}
	
	@Test
	void isHighCard_AddHighCardHand_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.HIGH_CARD));
		Assertions.assertTrue(hand.isHighCard());
	}
	
	@Test
	void isHighCard_AddRoyalFlush_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.ROYAL_FLUSH));
		Assertions.assertFalse(hand.isHighCard());
	}
	
	@Test
	void isOnePair_AddOnePairHand_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.ONE_PAIR));
		Assertions.assertTrue(hand.isOnePair());
	}
	
	@Test
	void isOnePair_AddHighCardHand_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.HIGH_CARD));
		Assertions.assertFalse(hand.isOnePair());
	}
	
	@Test
	void isTwoPair_AddTwoPairHand_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.TWO_PAIR));
		Assertions.assertTrue(hand.isTwoPair());
	}
	
	@Test
	void isTwoPair_AddStraightHand_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.STRAIGHT));
		Assertions.assertFalse(hand.isTwoPair());
	}
	
	@Test
	void isThreeOfAKind_AddThreeOfAKind_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.THREE_OF_A_KIND));
		Assertions.assertTrue(hand.isThreeOfAKind());
	}
	
	@Test
	void isThreeOfAKind_AddTwoPair_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.TWO_PAIR));
		Assertions.assertFalse(hand.isThreeOfAKind());
	}
	
	@Test
	void isFullHouse_AddFullHouse_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.FULL_HOUSE));
		Assertions.assertTrue(hand.isFullHouse());
	}
	
	@Test
	void isFullHouse_AddThreeOfAKind_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.THREE_OF_A_KIND));
		Assertions.assertFalse(hand.isFullHouse());
	}
	
	@Test
	void isFourOfAKind_AddFourOfAKind_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.FOUR_OF_A_KIND));
		Assertions.assertTrue(hand.isFourOfAKind());
	}
	
	@Test
	void isFourOfAKind_AddFullHouse_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.FULL_HOUSE));
		Assertions.assertFalse(hand.isFourOfAKind());
	}
	
	@Test
	void isStraight_AddStraight_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.STRAIGHT));
		Assertions.assertTrue(hand.isStraight());
	}
	
	@Test
	void isStraight_AddStraightALowCard_ShouldReturnFalse() throws IllegalHandException {
		List<Card> cardList = Arrays.asList(new Card(Rank.TWO, 		Suit.CLUBS),
										 	new Card(Rank.THREE, 	Suit.HEARTS),
										 	new Card(Rank.FOUR, 	Suit.CLUBS),
										 	new Card(Rank.FIVE, 	Suit.SPADES),
										 	new Card(Rank.A, 		Suit.DIAMONDS));
		PokerHand hand = new PokerHand(new ArrayList<Card>(cardList));
		Assertions.assertTrue(hand.isStraight());
	}
	
	@Test
	void isStraight_AddHighCard_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.HIGH_CARD));
		Assertions.assertFalse(hand.isStraight());
	}
	
	@Test
	void isFlush_AddFlush_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.FLUSH));
		Assertions.assertTrue(hand.isFlush());
	}
	
	@Test
	void isFlush_AddRoyalFlush_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.ROYAL_FLUSH));
		Assertions.assertFalse(hand.isFlush());
	}
	
	@Test
	void isStraightFlush_AddStraightFlush_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.STRAIGHT_FLUSH));
		Assertions.assertTrue(hand.isStraightFlush());
	}
	
	@Test
	void isStraightFlush_AddFlush_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.FLUSH));
		Assertions.assertFalse(hand.isStraightFlush());
	}
	
	@Test
	void isRoyalFlush_AddRoyalFlush_ShouldReturnTrue() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.ROYAL_FLUSH));
		Assertions.assertTrue(hand.isRoyalFlush());
	}
	
	@Test
	void isRoyalFlush_AddStraightFlush_ShouldReturnFalse() throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(PokerHandName.STRAIGHT_FLUSH));
		Assertions.assertFalse(hand.isRoyalFlush());
	}
	
	@DisplayName("Parameterized test for 'translateHand' method")
    @ParameterizedTest
    @MethodSource("generateArgumentsStream")
    void translateHand_SamplePokerHandFromEachNamedHand_ShouldPass(PokerHandName handName) throws IllegalHandException {
		PokerHand hand = new PokerHand(listOfTestCards(handName));
		String translation = hand.translateHand();
		Assertions.assertTrue(translation.equals(handName.getName()), String.format("Translated to '%s'", translation));
    }
	@SuppressWarnings(value = { "unused" })
	private static Stream<PokerHandName> generateArgumentsStream() {
	    List<PokerHandName> listOfArguments = new LinkedList<>();
	    for (PokerHandName pokerHandName : PokerHandName.values()) {
	    	listOfArguments.add(pokerHandName);
		}
	    return listOfArguments.stream();
	}
	/**
	 * Helper function: Returns a named poker hand for testing purposes
	 * 
	 * @param name	The enum type constant (PokerHandNames) for the requested hand
	 * @return		A full poker hand
	 */
	private List<Card> listOfTestCards(PokerHandName name) {
		
		List<Card> cardList = new ArrayList<Card>();
		
		switch (name) {
		case NO_NAME:
			cardList = Arrays.asList(new Card(Rank.TWO, 	Suit.HEARTS));
			break;
		case HIGH_CARD:
			cardList = Arrays.asList(new Card(Rank.K, 		Suit.DIAMONDS),
					   				 new Card(Rank.Q, 		Suit.DIAMONDS),
					   				 new Card(Rank.SEVEN, 	Suit.SPADES),
					   				 new Card(Rank.FOUR, 	Suit.SPADES),
					   				 new Card(Rank.THREE, 	Suit.HEARTS));
			break;
		case ONE_PAIR:
			cardList = Arrays.asList(new Card(Rank.T, 		Suit.SPADES),
					   				 new Card(Rank.T,	 	Suit.HEARTS),
					   				 new Card(Rank.EIGHT, 	Suit.SPADES),
					   				 new Card(Rank.SEVEN, 	Suit.HEARTS),
					   				 new Card(Rank.FOUR, 	Suit.CLUBS));
			break;
		case TWO_PAIR:
			cardList = Arrays.asList(new Card(Rank.J, 		Suit.HEARTS),
									 new Card(Rank.J, 		Suit.SPADES),
									 new Card(Rank.THREE, 	Suit.CLUBS),
									 new Card(Rank.THREE, 	Suit.SPADES),
									 new Card(Rank.TWO, 	Suit.HEARTS));
			break;
		case THREE_OF_A_KIND:
			cardList = Arrays.asList(new Card(Rank.Q, 		Suit.CLUBS),
									 new Card(Rank.Q, 		Suit.SPADES),
									 new Card(Rank.Q, 		Suit.HEARTS),
									 new Card(Rank.NINE, 	Suit.HEARTS),
									 new Card(Rank.TWO, 	Suit.SPADES));
			break;
		case STRAIGHT:
			cardList = Arrays.asList(new Card(Rank.T, 		Suit.DIAMONDS),
									 new Card(Rank.NINE, 	Suit.SPADES),
									 new Card(Rank.EIGHT, 	Suit.HEARTS),
									 new Card(Rank.SEVEN, 	Suit.DIAMONDS),
									 new Card(Rank.SIX, 	Suit.CLUBS));
			break;
		case FLUSH:
			cardList = Arrays.asList(new Card(Rank.J, 		Suit.DIAMONDS),
									 new Card(Rank.NINE, 	Suit.DIAMONDS),
									 new Card(Rank.EIGHT, 	Suit.DIAMONDS),
									 new Card(Rank.FOUR, 	Suit.DIAMONDS),
									 new Card(Rank.THREE, 	Suit.DIAMONDS));
			break;
		case FULL_HOUSE:
			cardList = Arrays.asList(new Card(Rank.SIX, 	Suit.SPADES),
									 new Card(Rank.SIX, 	Suit.HEARTS),
									 new Card(Rank.SIX, 	Suit.DIAMONDS),
									 new Card(Rank.K, 		Suit.CLUBS),
									 new Card(Rank.K, 		Suit.HEARTS));
			break;
		case FOUR_OF_A_KIND:
			cardList = Arrays.asList(new Card(Rank.FIVE, 	Suit.CLUBS),
									 new Card(Rank.FIVE, 	Suit.DIAMONDS),
									 new Card(Rank.FIVE, 	Suit.HEARTS),
									 new Card(Rank.FIVE, 	Suit.SPADES),
									 new Card(Rank.TWO, 	Suit.DIAMONDS));
			break;
		case STRAIGHT_FLUSH:
			cardList = Arrays.asList(new Card(Rank.J, 		Suit.CLUBS),
									 new Card(Rank.T, 		Suit.CLUBS),
									 new Card(Rank.NINE, 	Suit.CLUBS),
									 new Card(Rank.EIGHT, 	Suit.CLUBS),
									 new Card(Rank.SEVEN, 	Suit.CLUBS));
			break;
		case ROYAL_FLUSH:
			cardList = Arrays.asList(new Card(Rank.A, 		Suit.DIAMONDS),
									 new Card(Rank.K, 		Suit.DIAMONDS),
									 new Card(Rank.Q, 		Suit.DIAMONDS),
									 new Card(Rank.J, 		Suit.DIAMONDS),
									 new Card(Rank.T, 		Suit.DIAMONDS));
			break;
		default:
			break;
		}
		
		return new ArrayList<Card>(cardList);
	}
	
}
