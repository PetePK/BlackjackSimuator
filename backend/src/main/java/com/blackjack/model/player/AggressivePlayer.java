package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.model.GameState;
import com.blackjack.service.strategy.AggressiveStrategy;

public class AggressivePlayer extends Player {

    public AggressivePlayer(String name, double money) {
        super(name, money, new AggressiveStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, GameState gameState) {
        // Uses number of cards drawn as count (just like before)
        int count = gameState.getDeck().getCardCount();
        return strategy.shouldHit(hand.getCards(), dealerUpCard, count, gameState.getDeck().cardsRemaining());
    }
}
