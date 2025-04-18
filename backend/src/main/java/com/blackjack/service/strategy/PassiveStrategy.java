package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class PassiveStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count, int cardsRemaining) {
        int value = hand.stream().mapToInt(Card::getNumericValue).sum();
        return value < 14; // Passive logic: hits only on low totals
    }

    @Override
    public String getType() {
        return "passive";
    }
}
