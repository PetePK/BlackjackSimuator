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

    public GameState() {
        this.players = new ArrayList<>();
        this.dealer = new Dealer();
        this.deck = new Deck(6); // 👈 Use 6 decks for realistic shoe
        this.currentRound = 0;
    }

    // 🔹 Players
    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean removePlayer(String playerId) {
        return players.removeIf(p -> p.getId().equals(playerId));
    }

    public void resetPlayers() {
        players.clear();
    }

    // 🔹 Dealer
    public Dealer getDealer() {
        return dealer;
    }

    // 🔹 Deck
    public Deck getDeck() {
        return deck;
    }

    // 🔹 Rounds
    public int getCurrentRound() {
        return currentRound;
    }

    public void incrementRound() {
        currentRound++;
    }

    // 🔹 Full reset
    public void resetGame() {
        resetPlayers();
        dealer.resetHand();
        deck.shuffle();
        currentRound = 0;
    }
}
