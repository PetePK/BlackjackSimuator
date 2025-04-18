
package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.service.strategy.RandomStrategy;

public class DefaultPlayer extends Player {
    public DefaultPlayer(String name, double money) {
        super(name, money, new RandomStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, int count) {
        return strategy.shouldHit(hand.getCards(), dealerUpCard, count);
    }
}
