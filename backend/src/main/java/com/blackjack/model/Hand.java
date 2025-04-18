package com.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        if (card == null) throw new IllegalArgumentException("Card cannot be null");
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards); // Prevent external mutation
    }

    public int getValue() {
        int total = 0;
        int aceCount = 0;

        for (Card card : cards) {
            total += card.getNumericValue();
            if ("A".equals(card.getValue())) {
                aceCount++;
            }
        }

        // Adjust for Aces: reduce from 11 to 1 if total exceeds 21
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    public void clear() {
        cards.clear();
    }
}
