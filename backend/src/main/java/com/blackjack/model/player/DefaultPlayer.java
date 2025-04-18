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
        return getStrategy().shouldHit(
            getHand().getCards(),
            dealerUpCard,
            gameState.getRunningCount(),
            gameState.getDeck().cardsRemaining()
        );
    }
}
