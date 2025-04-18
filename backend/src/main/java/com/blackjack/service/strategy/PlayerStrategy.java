
package com.blackjack.service.strategy;

import com.blackjack.model.Card;
import java.util.List;

public interface PlayerStrategy {
    boolean shouldHit(List<Card> hand, Card dealerUpCard, int count);
}
