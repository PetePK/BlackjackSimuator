package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class AggressiveStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count, int cardsRemaining) {
        int value = hand.stream().mapToInt(Card::getNumericValue).sum();
        return value < 18; // Aggressive: hit more often
    }

    @Override
    public String getType() {
        return "aggressive";
    }
}
