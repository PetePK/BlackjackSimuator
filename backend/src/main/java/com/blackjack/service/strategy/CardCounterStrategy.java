
package com.blackjack.service.strategy;

import com.blackjack.model.Card;
import java.util.List;

public class CardCounterStrategy implements PlayerStrategy {
    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count) {
        int total = hand.stream().mapToInt(Card::getNumericValue).sum();
        if (count >= 2 && total >= 12) return false;  // Stand more with high count
        return total < 17;
    }
}
