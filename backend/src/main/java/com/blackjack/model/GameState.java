package com.blackjack.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.blackjack.model.player.Player;

@Component
public class GameState {

    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;
    private int currentRound;
    private int runningCount;

    public GameState() {
        this.players = new ArrayList<>();
        this.dealer = new Dealer();
        this.deck = new Deck(); // Default to 6 decks internally if needed
        this.currentRound = 0;
        this.runningCount = 0;
    }

    // --- Players ---
    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean removePlayer(String playerId) {
        return players.removeIf(p -> p.getId().equals(playerId));
    }

    public void clearPlayers() {
        players.clear();
    }

    // --- Dealer & Deck ---
    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    // --- Rounds ---
    public int getCurrentRound() {
        return currentRound;
    }

    public void incrementRound() {
        currentRound++;
    }

    // --- Running Count ---
    public int getRunningCount() {
        return runningCount;
    }

    public void resetRunningCount() {
        runningCount = 0;
    }

    public void incrementRunningCount(int value) {
        runningCount += value;
    }

    // --- Full Game Reset ---
    public void resetGame() {
        clearPlayers();
        dealer.resetHand();
        deck.shuffle();
        currentRound = 0;
        runningCount = 0;
    }
}
