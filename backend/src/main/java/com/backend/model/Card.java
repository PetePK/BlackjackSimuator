package com.backend.model;

public class Card {
    private String suit;    // "hearts", "diamonds", "clubs", "spades"
    private String value;   // "A","2",…,"10","J","Q","K"

    public Card() {}

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    public String getSuit() { return suit; }
    public void setSuit(String suit) { this.suit = suit; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
