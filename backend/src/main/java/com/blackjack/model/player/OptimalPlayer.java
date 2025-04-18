
package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.service.strategy.OptimalStrategy;

public class OptimalPlayer extends Player {
    public OptimalPlayer(String name, double money) {
        super(name, money, new OptimalStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, int count) {
        return strategy.shouldHit(hand.getCards(), dealerUpCard, count);
    }
}
