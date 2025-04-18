package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class OptimalStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int runningCount, int cardsRemaining) {
        int total = hand.stream().mapToInt(Card::getNumericValue).sum();
        boolean hasAce = hand.stream().anyMatch(card -> "A".equals(card.getValue()));
        int dealerValue = dealerUpCard.getNumericValue();

        if (hasAce && total <= 21) {
            // Soft hand logic
            if (total <= 17) return true;
            if (total == 18) return dealerValue >= 9;
            return false;
        }

        // Hard hand logic
        if (total <= 11) return true;
        if (total == 12) return !(dealerValue >= 4 && dealerValue <= 6);
        if (total >= 13 && total <= 16) return dealerValue >= 7;
        return false;
    }

    @Override
    public String getType() {
        return "optimal";
    }
}
