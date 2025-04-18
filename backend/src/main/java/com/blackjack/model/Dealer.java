
package com.blackjack.model;

public class Dealer {
    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public void resetHand() {
        hand.clear();
    }
}
