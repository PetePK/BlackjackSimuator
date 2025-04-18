package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.model.GameState;
import com.blackjack.service.strategy.OptimalStrategy;

public class OptimalPlayer extends Player {

    public OptimalPlayer(String name, double money) {
        super(name, money, new OptimalStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, GameState gameState) {
        return getStrategy().shouldHit(
            getHand().getCards(),
            dealerUpCard,
            gameState.getRunningCount(),
            gameState.getDeck().cardsRemaining()
        );
    }
}
