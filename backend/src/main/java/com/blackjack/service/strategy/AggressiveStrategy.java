package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class AggressiveStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count, int cardsRemaining) {
        int total = hand.stream().mapToInt(Card::getNumericValue).sum();
        return total < 19; // Aggressive players keep hitting until 19
    }

    @Override
    public String getType() {
        return "aggressive";
    }
}
