package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

/**
 * Strategy interface for determining player decisions in Blackjack.
 * Each strategy implements the logic of whether to hit or stand.
 */
public interface PlayerStrategy {

    /**
     * Determines whether the player should hit.
     *
     * @param hand the player's current hand
     * @param dealerUpCard the dealer's visible card
     * @param runningCount the card counter's running count (0 for non-counter strategies)
     * @param cardsRemaining cards left in the shoe (used for true count if needed)
     * @return true if the player should hit, false to stand
     */
    boolean shouldHit(List<Card> hand, Card dealerUpCard, int runningCount, int cardsRemaining);

    /**
     * Returns a short string identifier for the strategy.
     *
     * @return the strategy name
     */
    String getType();
}
