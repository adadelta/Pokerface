package com.instil.pokerface.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.instil.pokerface.exception.IllegalCardException;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An Enum type representing a card's suit
 */
@Getter
@AllArgsConstructor
public enum Suit {
	
	HEARTS		('H'),
	DIAMONDS	('D'),
	SPADES		('S'),
	CLUBS		('C');
	
	// A single character representing the suit
	private final Character suitCharacter;
	
	// A static map for character -> constant lookup
	private static final Map<Character, Suit> VALUE_MAP = Stream.of(values()).collect(Collectors.toMap(Suit::getSuitCharacter, Function.identity()));
	
	/**
	 * Gets the Suit according to it's character
	 * 
	 * @param c 	The character for a given Suit
	 * @return 		The Suit for the given Character
	 * @throws 		IllegalCardException thrown if the passed character does not exist
	 */
	public static Suit getSuitByCharacter(Character c) throws IllegalCardException {
		if(!VALUE_MAP.containsKey(c)) {
			throw new IllegalCardException(String.format("%c is not a valid suit", c));
		}
		return VALUE_MAP.get(c);
	}

}
