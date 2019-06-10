package com.instil.pokerface.model;

import com.instil.pokerface.exception.IllegalHandException;

/**
 * An interface for a hand of cards in a card game
 */
public interface Hand {
	
	/**
	 * This method counts how many cards are currently in the hand
	 * 
	 * @return 		An integer representing the number of cards in the hand
	 */
	public int countCardsInHand();
	
	/**
	 * This method returns the maximum number of cards a hand can hold
	 * 
	 * @return 		An integer representing the maximum number of cards
	 */
	public int getMaxHandSize();
	
	/**
	 * This method translates a given hand to it's name
	 * 
	 * @return 		A string translation of the hand, error text if hand is not valid
	 * @throws 		IllegalHandException
	 */
	public String translateHand() throws IllegalHandException;

}
