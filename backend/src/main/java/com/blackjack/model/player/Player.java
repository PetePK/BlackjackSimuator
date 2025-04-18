package com.blackjack.model.player;

import java.util.List;
import java.util.UUID;

import com.blackjack.model.Card;
import com.blackjack.model.GameState;
import com.blackjack.model.Hand;
import com.blackjack.service.strategy.PlayerStrategy;

public abstract class Player {
    private final String id;
    private String name;
    private double money;
    private double currentBet;
    private final Hand hand;
    private boolean active;
    private int wins;
    private int losses;
    private PlayerStrategy strategy;
    private String roundResult;

    public Player(String name, double money, PlayerStrategy strategy) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.money = money;
        this.currentBet = 10;
        this.hand = new Hand();
        this.active = true;
        this.wins = 0;
        this.losses = 0;
        this.strategy = strategy;
        this.roundResult = null;
    }

    // Abstract method for strategy-based decision
    public abstract boolean decideToHit(Card dealerUpCard, GameState gameState);

    // Game actions
    public void win(double amount) {
        money += amount;
        wins++;
    }

    public void lose(double amount) {
        money -= amount;
        losses++;
    }

    public void push() {
        // No money changes, but could be logged or extended
    }

    public void resetHand() {
        hand.clear();
        roundResult = null;
    }

    public void resetRecord() {
        wins = 0;
        losses = 0;
    }

    // Accessors
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isActive() {
        return active;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getRoundResult() {
        return roundResult;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getHandValue() {
        return hand.getValue();
    }

    public PlayerStrategy getStrategy() {
        return strategy;
    }

    public String getPlayerType() {
        return strategy.getType();
    }

    // Mutators
    public void setMoney(double money) {
        this.money = money;
    }

    public void setCurrentBet(double currentBet) {
        this.currentBet = currentBet;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoundResult(String result) {
        this.roundResult = result;
    }

    public void setStrategy(PlayerStrategy strategy) {
        this.strategy = strategy;
    }

    public void setName(String name) {
        this.name = name;
    }
}
