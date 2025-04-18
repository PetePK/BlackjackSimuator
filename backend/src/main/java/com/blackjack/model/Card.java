package com.blackjack.model;

public final class Card {
    private final String suit;
    private final String value;
    private final int numericValue;

    public Card(String suit, String value, int numericValue) {
        if (suit == null || value == null) {
            throw new IllegalArgumentException("Suit and value must not be null");
        }
        this.suit = suit;
        this.value = value;
        this.numericValue = numericValue;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int getNumericValue() {
        return numericValue;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
