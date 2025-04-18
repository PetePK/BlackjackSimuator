package com.blackjack.model;

import java.util.List;

public class Dealer {
    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public void resetHand() {
        hand.clear();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getHandValue() {
        return hand.getValue();
    }
}
