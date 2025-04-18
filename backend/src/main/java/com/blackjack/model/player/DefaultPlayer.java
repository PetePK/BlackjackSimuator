package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.model.GameState;
import com.blackjack.service.strategy.RandomStrategy;

public class DefaultPlayer extends Player {

    public DefaultPlayer(String name, double money) {
        super(name, money, new RandomStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, GameState gameState) {
        int count = gameState.getDeck().getCardCount();
        return strategy.shouldHit(hand.getCards(), dealerUpCard, count, gameState.getDeck().cardsRemaining());
    }
}
