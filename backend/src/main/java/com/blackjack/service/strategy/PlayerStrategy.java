package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public interface PlayerStrategy {
    boolean shouldHit(List<Card> hand, Card dealerUpCard, int runningCount, int cardsRemaining);
    String getType();
}
