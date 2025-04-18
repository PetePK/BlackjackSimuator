
package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.service.strategy.AggressiveStrategy;

public class AggressivePlayer extends Player {
    public AggressivePlayer(String name, double money) {
        super(name, money, new AggressiveStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, int count) {
        return strategy.shouldHit(hand.getCards(), dealerUpCard, count);
    }
}
