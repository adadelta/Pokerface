package com.instil.pokerface.model;

/**
 * A class representing a hand in a game of Poker
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.instil.pokerface.exception.IllegalHandException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PokerHand implements Hand {
	
	/**
	 * An Enum type representing all named poker hands 
	 */
	@Getter
	protected enum PokerHandName {
		NO_NAME 		("No name"),
		HIGH_CARD 		("High card"),
		ONE_PAIR 		("One pair"),
		TWO_PAIR 		("Two pair"),
		THREE_OF_A_KIND ("Three of a kind"),
		STRAIGHT 		("Straight"),
		FLUSH 			("Flush"),
		FULL_HOUSE 		("Full house"),
		FOUR_OF_A_KIND 	("Four of a kind"),
		STRAIGHT_FLUSH 	("Straight flush"),
		ROYAL_FLUSH 	("Royal flush");
		private final String name;
		private PokerHandName(String name) {this.name = name;}
	}
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final int MAX_SIZE = 5;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private TreeSet<Card> cardSet = new TreeSet<Card>();
	
	public PokerHand() {
	}
	
	public PokerHand(List<Card> cards) throws IllegalHandException {
		if(cards.size() > MAX_SIZE) {
			String message = "Hand can only hold %d cards, not %d.";
			throw new IllegalHandException(String.format(message, MAX_SIZE, cards.size()));
		}
		cardSet.addAll(cards);
	}
	
	@Override
	public String translateHand() throws IllegalHandException {
		if(cardSet.size() != MAX_SIZE) {
			return PokerHandName.NO_NAME.getName();
		}
		
		// #0 High Card: 50.18% chance of return if cards are sampled from a random distribution
		if(isHighCard()) {return PokerHandName.HIGH_CARD.getName();};
		
		// #1 Single Pair: 42.26%
		if(isOnePair()) {return PokerHandName.ONE_PAIR.getName();};
		
		// #2 Two pair: 4.80%
		if(isTwoPair()) {return PokerHandName.TWO_PAIR.getName();};
		
		// #3 Three of a kind: 2.11%
		if(isThreeOfAKind()) {return PokerHandName.THREE_OF_A_KIND.getName();};
		
		// #4 Full house: < 0%
		if(isFullHouse()) {return PokerHandName.FULL_HOUSE.getName();};
		
		// #5 Four of a kind: < 0%
		if(isFourOfAKind()) {return PokerHandName.FOUR_OF_A_KIND.getName();};
		
		// #6 Straight: < 0%
		if(isStraight()) {return PokerHandName.STRAIGHT.getName();};
		
		// #7 Flush: < 0%
		if(isFlush()) {return PokerHandName.FLUSH.getName();};
		
		// #8 Straight flush: < 0%
		if(isStraightFlush()) {return PokerHandName.STRAIGHT_FLUSH.getName();};
		
		// #9 Royal flush: < 0%
		if(isRoyalFlush()) {return PokerHandName.ROYAL_FLUSH.getName();};
		
		return PokerHandName.NO_NAME.getName();
	}
	
	@Override
	public int countCardsInHand() {
		return cardSet.size();
	}
	
	@Override
	public int getMaxHandSize() {
		return MAX_SIZE;
	}
	
	/**
	 * Returns all the cards currently in the hand
	 * 
	 * @return		An List with all the cards in the hand
	 */
	public List<Card> getCards() {
		return new ArrayList<Card>(cardSet);
	}
	
	/**
	 * Checks if the hand contains a given card
	 * 
	 * @param card	A Card object
	 * @return		True if the Card object is in the hand, false otherwise
	 */
	public boolean containsCard(Card card) {
		return cardSet.contains(card);
	}
	
	/**
	 * Adds a card to the hand
	 * 
	 * @param card	The card that should be added to the hand
	 * @throws 		IllegalHandException thrown if hand is full beforehand
	 */
	public void addCardToHand(Card card) throws IllegalHandException {
		if(cardSet.size() == MAX_SIZE) {
			String message = "Hand already has the maximum number of allowed cards (%d)";
			throw new IllegalHandException(String.format(message, MAX_SIZE));
		}
		cardSet.add(card);
	}
	
	/**
	 * Helper function: Checks if the current hand is a High Card
	 * 					A High Card is a no pair hand that does not fall into any other category
	 * 
	 * @return 			True if hand is a Royal Flush, otherwise False
	 */
	protected boolean isHighCard() {
		int uniqueRanks = cardSet.stream().collect(Collectors.groupingBy(Card::getRank)).size();
		return uniqueRanks == 5 && !isSameSuit() && !isStraight();
	}
	
	/**
	 * Helper function: Checks if the current hand is a One Pair
	 * 					A One Pair hand is a hand that contains two cards of one rank and three cards of three other ranks
	 * 
	 * @return 			True if hand is a One Pair, otherwise False
	 */
	protected boolean isOnePair() {
		int uniqueRanks = cardSet.stream().collect(Collectors.groupingBy(Card::getRank)).size();
		return uniqueRanks == 4 && !isSameSuit();
	}
	
	/**
	 * Helper function: Checks if the current hand is a Two Pair
	 * 					A Two Pair hand is a hand that contains two cards of one rank, two cards of another rank 
	 * 					and one card of a third rank
	 * 
	 * @return 			True if hand is a Two Pair, otherwise False
	 */
	protected boolean isTwoPair() {
		Map<Rank, List<Card>> rankBuckets = cardSet.stream().collect(Collectors.groupingBy(Card::getRank));
		if(rankBuckets.size() == 3 && !isSameSuit()) {
			for(List<Card> cardList : rankBuckets.values()) {
				if(cardList.size() == 2) {return true;}; // E.g. Bucket1: 1 card, Bucket2: 2 card, Bucket3: 2 card
			}
		}
		return false;
	}
	
	/**
	 * Helper function: Checks if the current hand is a Three Of A Kind
	 * 					A Three Of A Kind hand is a hand that contains three cards of one rank and two cards 
	 * 					of two other ranks
	 * 
	 * @return 			True if hand is a Three Of A Kind, otherwise False
	 */
	protected boolean isThreeOfAKind() {
		Map<Rank, List<Card>> rankBuckets = cardSet.stream().collect(Collectors.groupingBy(Card::getRank));
		if(rankBuckets.size() == 3 && !isSameSuit()) {
			for(List<Card> cardList : rankBuckets.values()) {
				if(cardList.size() == 3) {return true;}; // E.g. Bucket1: 1 card, Bucket2: 3 card, Bucket3: 1 card
			}
		}
		return false;
	}
	
	/**
	 * Helper function: Checks if the current hand is a Full House
	 * 					A full house is a hand that contains three cards of one rank and two cards of another rank
	 * 
	 * @return 			True if hand is a Full House, otherwise False
	 */
	protected boolean isFullHouse() {
		Map<Rank, List<Card>> rankBuckets = cardSet.stream().collect(Collectors.groupingBy(Card::getRank));
		if(rankBuckets.size() == 2 && !isSameSuit()) {
			int randomBucketSize = rankBuckets.values().stream().findFirst().get().size();
			return (randomBucketSize == 2 || randomBucketSize == 3); // E.g. Bucket1: 2, Bucket2: 3
		}
		return false;
	}
	
	/**
	 * Helper function: Checks if the current hand is a Four Of A Kind
	 * 					Four Of A Kind is a hand that contains four cards of one rank and one card of another rank
	 * @return 			True if hand is a Four Of A Kind, otherwise False
	 */
	protected boolean isFourOfAKind() {
		Map<Rank, List<Card>> rankBuckets = cardSet.stream().collect(Collectors.groupingBy(Card::getRank));
		if(rankBuckets.size() == 2 && !isSameSuit()) {
			int randomBucketSize = rankBuckets.values().stream().findFirst().get().size();
			return randomBucketSize == 1 || randomBucketSize == 4; // E.g. Bucket1: 1, Bucket2: 4
		}
		return false;
	}
	
	/**
	 * Helper function: Checks if the current hand is Straight
	 * 					A Straight is a hand that contains five cards of sequential rank
	 * 
	 * @return 			True if hand is a Straight, otherwise False
	 */
	protected boolean isStraight() {
		return isSequental() && !isSameSuit();
	}
	
	/**
	 * Helper function: Checks if the current hand is a Flush
	 * 					A Flush is a hand where all the cards are of the same suit
	 * 
	 * @return 			True if hand is a Flush, otherwise False
	 */
	protected boolean isFlush() {
		return !isSequental() && isSameSuit();
	}
	
	/**
	 * Helper function: Checks if the current hand is a Straight Flush
	 * 					A Straight flush is hand that contains five cards of 
	 * 					sequential rank as well as all of them being the same suit
	 * 
	 * @return 			True if hand is a Straight Flush, otherwise False
	 */
	protected boolean isStraightFlush() {
		return isSequental() && isSameSuit() && !isRoyalFlush();
	}
	
	/**
	 * Helper function: Checks if the current hand is a Royal Flush
	 * 					A Royal flush is an ace-high straight flush
	 * 
	 * @return 			True if hand is a Royal Flush, otherwise False
	 */
	protected boolean isRoyalFlush() {
		return cardSet.last().getRank() == Rank.A  && // Is the last card an Ace
			   cardSet.first().getRank() == Rank.T && // Is the first card a Ten
			   isSameSuit();
	}
	
	/**
	 * Helper function: Checks if the cards in a hand are of the same sort
	 * 
	 * @return True if all cards are of same suit, false otherwise
	 */
	private boolean isSameSuit() {
		Suit suit = cardSet.first().getSuit();
		return cardSet.stream().allMatch(c -> c.getSuit() == suit);
	}
	
	/**
	 * Helper function: Checks if the cards in a hand are in sequential order
	 * 
	 * @return			True if rank is in sequential order, false otherwise
	 */
	private boolean isSequental() {
		Integer lowestCardRankOrder = cardSet.first().getRank().getRankOrder();
		Integer highestCardRankOrder = cardSet.last().getRank().getRankOrder();
		
		// 'A' can either be the highest or the lowest card in a hand. If our current lowest card is 'Two'
		// and our highest card is 'A', then we want to treat 'A' as the lowest card (for a possible Straight)
		if(cardSet.first().getRank() == Rank.TWO && cardSet.last().getRank() == Rank.A) {
			Card secondHighestCard = cardSet.floor(new Card(Rank.K, Suit.HEARTS));
			highestCardRankOrder = secondHighestCard.getRank().getRankOrder();
			lowestCardRankOrder = 0; // A
		}
		
		return highestCardRankOrder - lowestCardRankOrder == 4; // Value of highest card should be 4 points higher than lowest
	}
	
}
