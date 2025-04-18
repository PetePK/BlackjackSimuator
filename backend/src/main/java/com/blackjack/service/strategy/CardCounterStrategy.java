package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class CardCounterStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count) {
        int total = hand.stream().mapToInt(Card::getNumericValue).sum();
        // boolean hasAce = hand.stream().anyMatch(card -> card.getValue().equals("A"));
        int dealerValue = dealerUpCard.getNumericValue();

        // Update logic using running count
        if (count > 0) {
            // Positive count (favorable to player)
            if (total <= 11) return true;  // Always hit on low totals
            if (total == 12 && dealerValue >= 4 && dealerValue <= 6) return false;  // Stand on 12 if dealer has 4-6
            if (total >= 13 && total <= 16) return dealerValue >= 7;  // Hit if dealer shows 7 or higher
            return false;
        } else {
            // Negative or neutral count (favorable to dealer)
            if (total <= 11) return true;  // Always hit on low totals
            if (total == 12 && dealerValue >= 4 && dealerValue <= 6) return false;  // Stand on 12 if dealer has 4-6
            if (total >= 13 && total <= 16) return dealerValue >= 7;  // Hit if dealer shows 7 or higher
            return false;
        }
    }

    @Override
    public String getType() {
        return "card counter";
    }
}
