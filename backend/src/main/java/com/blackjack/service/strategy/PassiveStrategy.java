
package com.blackjack.service.strategy;

import com.blackjack.model.Card;
import java.util.List;

public class PassiveStrategy implements PlayerStrategy {
    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count) {
        int total = hand.stream().mapToInt(Card::getNumericValue).sum();
        return total < 14;
    }
}
