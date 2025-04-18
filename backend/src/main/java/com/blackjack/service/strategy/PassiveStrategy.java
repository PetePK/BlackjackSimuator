package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class PassiveStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int runningCount, int cardsRemaining) {
        int handValue = hand.stream().mapToInt(Card::getNumericValue).sum();
        return handValue < 12;
    }

    @Override
    public String getType() {
        return "passive";
    }
}
