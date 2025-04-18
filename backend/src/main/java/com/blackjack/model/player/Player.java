package com.blackjack.model.player;

import java.util.List;
import java.util.UUID;

import com.blackjack.model.Card;
import com.blackjack.model.GameState;
import com.blackjack.model.Hand;
import com.blackjack.service.strategy.PlayerStrategy;

public abstract class Player {
    protected String id;
    protected String name;
    protected double money;
    protected double currentBet;
    protected Hand hand;
    protected boolean active;
    protected int wins;
    protected int losses;
    protected PlayerStrategy strategy;
    protected String roundResult;

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

    public abstract boolean decideToHit(Card dealerUpCard, GameState gameState);

    public void win(double amount) {
        money += amount;
        wins++;
    }

    public void lose(double amount) {
        money -= amount;
        losses++;
    }

    public void push() {
        // no-op by default
    }

    public void resetHand() {
        hand.clear();
        roundResult = null;
    }

    public void resetRecord() {
        this.wins = 0;
        this.losses = 0;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getMoney() { return money; }
    public double getCurrentBet() { return currentBet; }
    public Hand getHand() { return hand; }
    public boolean isActive() { return active; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public String getRoundResult() { return roundResult; }

    // Setters
    public void setMoney(double money) { this.money = money; }
    public void setCurrentBet(double currentBet) { this.currentBet = currentBet; }
    public void setActive(boolean active) { this.active = active; }
    public void setRoundResult(String result) { this.roundResult = result; }

    public List<Card> getCards() { return hand.getCards(); }
    public int getHandValue() { return hand.getValue(); }
}
