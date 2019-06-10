package com.instil.pokerface.utils;

import java.util.ArrayList;
import java.util.List;

import com.instil.pokerface.exception.IllegalCardException;
import com.instil.pokerface.exception.IllegalHandException;
import com.instil.pokerface.model.Card;
import com.instil.pokerface.model.PokerHand;
import com.instil.pokerface.model.Rank;
import com.instil.pokerface.model.Suit;

/**
 * This class offers static utility methods for parsing cards and hands
 */
public class CardParser {
	
	/**
	 * Parses a string of cards and returns a poker hand
	 * 
	 * @param cardString				A string with the format RS RS RS ...,  where R is the rank and S is the suit, e.g. AD, 2S, 5D, 6H, TC
	 * @return							PokerHand object
	 * @throws IllegalCardException		Thrown if a card is illegal
	 * @throws IllegalHandException		Thrown if a hand is illegal
	 */
	public static PokerHand parseHand(String cardString) throws IllegalCardException, IllegalHandException {
		String[] cards = cardString.split(" ");
		List<Card> cardList = new ArrayList<Card>();
		for (String card : cards) {
			cardList.add(parseCard(card));
		}
		return new PokerHand(cardList);
	}
	
	/**
	 * Parses a card string and returns a Card object
	 * 
	 * @param cardString 				A string with the format "RS", where R is the rank and S is the suit, e.g. QD.
	 * @return 							Card object
	 * @throws IllegalCardException		Thrown if a card is illegal
	 */
	public static Card parseCard(String cardString) throws IllegalCardException {
		char[] charArray = cardString.toCharArray();
		return new Card(Rank.getRankByCharacter(charArray[0]), Suit.getSuitByCharacter(charArray[1]));
	}

}
