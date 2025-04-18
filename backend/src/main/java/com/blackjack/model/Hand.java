
package com.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getValue() {
        int total = 0;
        int aces = 0;

        for (Card card : cards) {
            total += card.getNumericValue();
            if (card.getValue().equals("A")) aces++;
        }

        // Adjust for Aces
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    public void clear() {
        cards.clear();
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }
}
