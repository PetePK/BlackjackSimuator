package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class CardCounterStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int runningCount, int cardsRemaining) {
        int total = hand.stream().mapToInt(Card::getNumericValue).sum();
        boolean hasAce = hand.stream().anyMatch(card -> card.getValue().equals("A"));
        int dealerValue = dealerUpCard.getNumericValue();

        double decksRemaining = Math.max(1.0, cardsRemaining / 52.0);
        double trueCount = runningCount / decksRemaining;

        // 🔹 Soft hands logic
        if (hasAce && total <= 21) {
            if (total <= 17) return true;
            if (total == 18) return dealerValue >= 9 || trueCount <= 0;
            return false;
        }

        // 🔹 Hard hands logic — dynamic based on true count
        if (total <= 11) return true;

        if (total == 12) {
            if (dealerValue >= 4 && dealerValue <= 6) return false;
            return trueCount <= 2;
        }

        if (total >= 13 && total <= 16) {
            if (dealerValue >= 7) return trueCount <= 1;
            return false;
        }

        // 🔹 Stand on hard 17 or higher
        return false;
    }

    @Override
    public String getType() {
        return "card counter";
    }
}
