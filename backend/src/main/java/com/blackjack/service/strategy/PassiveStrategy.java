package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class PassiveStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count) {
        int value = hand.stream().mapToInt(Card::getNumericValue).sum();
        return value < 14; // Example passive logic
    }

    @Override
    public String getType() {
        return "passive";
    }
}
