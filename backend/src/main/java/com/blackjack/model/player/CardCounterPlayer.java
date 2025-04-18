package com.blackjack.model.player;

import com.blackjack.model.Card;
import com.blackjack.model.GameState;
import com.blackjack.service.strategy.CardCounterStrategy;

public class CardCounterPlayer extends Player {

    private int runningCount = 0;

    public CardCounterPlayer(String name, double money) {
        super(name, money, new CardCounterStrategy());
    }

    @Override
    public boolean decideToHit(Card dealerUpCard, GameState gameState) {
        return getStrategy().shouldHit(
            getHand().getCards(),
            dealerUpCard,
            runningCount,
            gameState.getDeck().cardsRemaining()
        );
    }

    public void updateRunningCount(int delta) {
        runningCount += delta;
    }

    public void resetCount() {
        runningCount = 0;
    }

    public int getRunningCount() {
        return runningCount;
    }
}
