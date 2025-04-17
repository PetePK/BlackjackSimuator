package com.backend.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a blackjack player (or dealer).
 */
public class Player {
    private String id;
    private String name;
    private int money;
    private String strategy;        // e.g. "Conservative","Aggressive","Random"
    private boolean cardCounting;
    private boolean bettingStrategy;
    private int currentBet;
    private List<Card> cards = new ArrayList<>();
    private int handValue;
    private boolean active;
    private int wins;
    private int losses;

    public Player() {
        // no-arg constructor for Jackson
    }

    /**
     * Constructor used when adding a new player.
     */
    public Player(String id,
                  String name,
                  int money,
                  String strategy,
                  boolean cardCounting,
                  boolean bettingStrategy,
                  int currentBet) {
        this.id              = id;
        this.name            = name;
        this.money           = money;
        this.strategy        = strategy;
        this.cardCounting    = cardCounting;
        this.bettingStrategy = bettingStrategy;
        this.currentBet      = currentBet;
        this.active          = true;
        this.wins            = 0;
        this.losses          = 0;
    }

    // ─── Getters & Setters ─────────────────────────────────────────

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public String getStrategy() {
        return strategy;
    }
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public boolean isCardCounting() {
        return cardCounting;
    }
    public void setCardCounting(boolean cardCounting) {
        this.cardCounting = cardCounting;
    }

    public boolean isBettingStrategy() {
        return bettingStrategy;
    }
    public void setBettingStrategy(boolean bettingStrategy) {
        this.bettingStrategy = bettingStrategy;
    }

    public int getCurrentBet() {
        return currentBet;
    }
    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getHandValue() {
        return handValue;
    }
    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }

    // ─── Helper Methods ────────────────────────────────────────────

    /** Add a card to this player's hand. */
    public void addCard(Card card) {
        cards.add(card);
    }

    /** Clears the player's hand. */
    public void clearHand() {
        cards.clear();
        handValue = 0;
    }

    /** Increment win count by one. */
    public void incrementWins() {
        wins++;
    }

    /** Increment loss count by one. */
    public void incrementLosses() {
        losses++;
    }

    @Override
    public String toString() {
        return "Player{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", money=" + money +
               ", strategy='" + strategy + '\'' +
               ", currentBet=" + currentBet +
               ", handValue=" + handValue +
               ", active=" + active +
               ", wins=" + wins +
               ", losses=" + losses +
               '}';
    }
}
