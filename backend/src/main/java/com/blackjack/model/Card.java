
package com.blackjack.model;

public class Card {
    private String suit;
    private String value;
    private int numericValue;

    public Card(String suit, String value, int numericValue) {
        this.suit = suit;
        this.value = value;
        this.numericValue = numericValue;
    }

    public String getSuit() { return suit; }
    public String getValue() { return value; }
    public int getNumericValue() { return numericValue; }

    public void setSuit(String suit) { this.suit = suit; }
    public void setValue(String value) { this.value = value; }
    public void setNumericValue(int numericValue) { this.numericValue = numericValue; }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
