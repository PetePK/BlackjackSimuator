
package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.service.strategy.CardCounterStrategy;

public class CardCounterPlayer extends Player {
    public CardCounterPlayer(String name, double money) {
        super(name, money, new CardCounterStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, int count) {
        return strategy.shouldHit(hand.getCards(), dealerUpCard, count);
    }
}
