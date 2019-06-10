package com.instil.pokerface.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.instil.pokerface.exception.IllegalCardException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An Enum type representing a card's rank
 */
@Getter
@AllArgsConstructor
public enum Rank {
	
	TWO		('2', 1),
	THREE	('3', 2),
	FOUR	('4', 3),
	FIVE	('5', 4),
	SIX		('6', 5),
	SEVEN	('7', 6),
	EIGHT	('8', 7),
	NINE	('9', 8),
	T		('T', 9),
	J		('J', 10),
	Q		('Q', 11),
	K		('K', 12),
	A 		('A', 13);
	
	// A single character representing the suit
	private final Character rankCharacter;
	
	// Knowing the order of the cards might come in handy outside of the scope of this class,
	// e.g. for comparators
	private final Integer rankOrder;
	
	// A static map for character -> constant lookup
	@Getter(AccessLevel.NONE)
	private static final Map<Character, Rank> VALUE_MAP = Stream.of(values()).collect(Collectors.toMap(Rank::getRankCharacter, Function.identity()));
	
	/**
	 * Gets the Rank according to it's character
	 * 
	 * @param c		The character for a given Suit
	 * @return 		The Rank for a given character
	 * @throws 		IllegalCardException thrown if the passed character does not exist
	 */
	public static Rank getRankByCharacter(Character c) throws IllegalCardException {
		if(!VALUE_MAP.containsKey(c)) {
			throw new IllegalCardException(String.format("%c is not a valid rank", c));
		}
		return VALUE_MAP.get(c);
	}

}
