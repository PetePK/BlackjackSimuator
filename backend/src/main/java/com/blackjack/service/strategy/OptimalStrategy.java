package com.blackjack.service.strategy;

import java.util.List;

import com.blackjack.model.Card;

public class OptimalStrategy implements PlayerStrategy {

    @Override
    public boolean shouldHit(List<Card> hand, Card dealerUpCard, int count) {
        int total = hand.stream().mapToInt(Card::getNumericValue).sum();
        boolean hasAce = hand.stream().anyMatch(card -> card.getValue().equals("A"));

        int dealerValue = dealerUpCard.getNumericValue();

        if (hasAce && total <= 21) {
            // Soft totals
            if (total <= 17) return true;
            if (total == 18) return dealerValue >= 9;
            return false;
        } else {
            // Hard totals
            if (total <= 11) return true;
            if (total == 12) return !(dealerValue >= 4 && dealerValue <= 6);
            if (total >= 13 && total <= 16) return dealerValue >= 7;
            return false;
        }
    }

    @Override
    public String getType() {
        return "optimal";
    }
}
