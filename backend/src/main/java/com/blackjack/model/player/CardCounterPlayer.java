package com.blackjack.model.player;

import java.util.List;

import com.blackjack.model.Card;
import com.blackjack.service.strategy.CardCounterStrategy;

public class CardCounterPlayer extends Player {
    private int runningCount = 0;  // Track the running count for the card counter strategy

    public CardCounterPlayer(String name, double money) {
        super(name, money, new CardCounterStrategy());  // Passes the CardCounterStrategy to Player class
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, int count) {
        // Update running count before making a decision
        updateRunningCount(hand.getCards());
        return strategy.shouldHit(hand.getCards(), dealerUpCard, runningCount);  // Use the updated running count
    }

    // Method to update running count for the cards in the hand
    private void updateRunningCount(List<Card> hand) {
        for (Card card : hand) {
            if (card.getValue().equals("2") || card.getValue().equals("3") || card.getValue().equals("4") || card.getValue().equals("5") || card.getValue().equals("6")) {
                runningCount++;  // Increase count for low cards
            } else if (card.getValue().equals("10") || card.getValue().equals("J") || card.getValue().equals("Q") || card.getValue().equals("K") || card.getValue().equals("A")) {
                runningCount--;  // Decrease count for high cards
            }
        }
    }

    // Optionally, expose the running count for debugging or UI purposes
    public int getRunningCount() {
        return runningCount;
    }
}
