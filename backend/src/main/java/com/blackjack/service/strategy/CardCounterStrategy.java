package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class CardCounterStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int runningCount, int cardsRemaining) {
        int total = hand.stream().mapToInt(Card::getNumericValue).sum();
        boolean hasAce = hand.stream().anyMatch(card -> card.getValue().equals("A"));
        int dealerValue = dealerUpCard.getNumericValue();

        double decksRemaining = Math.max(1.0, (double) cardsRemaining / 52.0);
        double trueCount = runningCount / decksRemaining;

        // Handle soft hands
        if (hasAce && total <= 21) {
            if (total <= 17) return true;
            if (total == 18) {
                if (dealerValue >= 9 || dealerValue == 1) return trueCount <= 1; // Stand if TC > 1
                return false;
            }
            return false;
        }

        // Basic strategy + index deviations for hard totals
        if (total <= 11) return true;

        if (total == 12) {
            if (dealerValue >= 4 && dealerValue <= 6) return false;
            if ((dealerValue == 2 || dealerValue == 3) && trueCount >= 3) return false;
            return true;
        }

        if (total == 13 || total == 14) {
            if (dealerValue >= 2 && dealerValue <= 6) return false;
            if (dealerValue == 5 && trueCount >= -1) return false;
            return true;
        }

        if (total == 15) {
            if (dealerValue == 10 && trueCount >= 4) return false; // Stand vs 10 if TC >= 4
            return dealerValue >= 7;
        }

        if (total == 16) {
            if (dealerValue == 10 && trueCount >= 0) return false; // Stand vs 10 if TC >= 0
            if (dealerValue == 9 && trueCount >= 5) return false;
            return dealerValue >= 7;
        }

        if (total >= 17) return false;

        return false;
    }

    @Override
    public String getType() {
        return "card counter";
    }
}
