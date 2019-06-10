package com.instil.pokerface.model;

import lombok.Data;

/**
 * A class representing a playing card
 */
@Data
public class Card implements Comparable<Card> {
	private final Rank rank;
	private final Suit suit;
	
	@Override
	public int compareTo(Card otherCard) {
		// Cards with lower ranks should be earlier in the hand
		int diff = this.getRank().getRankOrder() - otherCard.getRank().getRankOrder();
		if(diff == 0) {
			// If ranks are the same then the suit dictates their ordering
			return this.getSuit().getSuitCharacter() - otherCard.getSuit().getSuitCharacter(); 
		}
		return diff;
	}
	
}
