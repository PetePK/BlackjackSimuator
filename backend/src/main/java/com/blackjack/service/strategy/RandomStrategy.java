package com.blackjack.service.strategy;

import java.util.List;
import java.util.Random;

import com.blackjack.model.Card;

public class RandomStrategy implements PlayerStrategy {

    private final Random random = new Random();

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count, int cardsRemaining) {
        return random.nextBoolean(); // Randomly decides to hit or stand
    }

    @Override
    public String getType() {
        return "random";
    }
}
