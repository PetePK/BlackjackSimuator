
package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.service.strategy.PassiveStrategy;

public class PassivePlayer extends Player {
    public PassivePlayer(String name, double money) {
        super(name, money, new PassiveStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, int count) {
        return strategy.shouldHit(hand.getCards(), dealerUpCard, count);
    }
}
