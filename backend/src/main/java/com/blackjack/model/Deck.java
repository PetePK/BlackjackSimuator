package com.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Deck {
    private static final int DECK_COUNT = 6;
    private final List<Card> cards;
    private int cardCount = 0;

    public Deck() {
        this.cards = new ArrayList<>();
        for (int i = 0; i < DECK_COUNT; i++) {
            initializeDeck();
        }
        shuffle();
    }

    private void initializeDeck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

        for (String suit : suits) {
            for (String value : values) {
                int numericValue = switch (value) {
                    case "A" -> 11;
                    case "J", "Q", "K" -> 10;
                    default -> Integer.parseInt(value);
                };
                cards.add(new Card(suit, value, numericValue));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
        cardCount = 0;
    }

    public Card draw() {
        if (cardsRemaining() < 52) {
            shuffle();
        }
        return cards.get(cardCount++);
    }

    public int cardsRemaining() {
        return cards.size() - cardCount;
    }

    public int getCardCount() {
        return cardCount;
    }

    public int getDeckCount() {
        return DECK_COUNT;
    }

    public int getTotalCards() {
        return cards.size();
    }
}
