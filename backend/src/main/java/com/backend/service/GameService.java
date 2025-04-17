package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.backend.model.Card;
import com.backend.model.DealerInfo;
import com.backend.model.Player;

@Service
public class GameService {

    private static final int DEFAULT_MONEY = 1000;
    private static final int DEFAULT_BET   = 50;
    private static final int MAX_PLAYERS   = 8;
    private static final int DEALER_STAND  = 17;

    // In‑memory storage of players
    private final Map<String, Player> players = new ConcurrentHashMap<>();

    // Shoe of 6 decks
    private Shoe shoe = new Shoe(6);

    // Dealer state
    private final DealerInfo dealer = new DealerInfo();

    /** ───── Player Management ───── **/

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players.values());
    }

    public Player addPlayer(Player p) {
        if (players.size() >= MAX_PLAYERS) {
            throw new IllegalStateException("Maximum of " + MAX_PLAYERS + " players reached.");
        }
        // Apply defaults
        if (p.getMoney() <= 0) p.setMoney(DEFAULT_MONEY);
        if (p.getCurrentBet() <= 0) p.setCurrentBet(DEFAULT_BET);
        p.setActive(true);
        players.put(p.getId(), p);
        return p;
    }

    public void removePlayer(String id) {
        players.remove(id);
    }

    public Player updatePlayer(Player updated) {
        players.put(updated.getId(), updated);
        return updated;
    }

    public void togglePlayerActive(String id) {
        Player p = players.get(id);
        if (p != null) {
            p.setActive(!p.isActive());
        }
    }

    public void resetAllPlayers() {
        // Reset each player’s state
        for (Player p : players.values()) {
            p.clearHand();
            p.setHandValue(0);
            p.setMoney(DEFAULT_MONEY);
            p.setCurrentBet(DEFAULT_BET);
            p.setWins(0);
            p.setLosses(0);
            p.setActive(true);
        }
        // Also reshuffle deck & clear dealer
        shoe = new Shoe(6);
        dealer.clearHand();
    }

    /** ───── Deck & Dealer ───── **/

    public void shuffleDeck() {
        shoe = new Shoe(6);
        dealer.clearHand();
    }

    public DealerInfo getDealerInfo() {
        return dealer;
    }

    /** ───── Round Simulation ───── **/

    public void playRound() {
        // 1) Dealer draws
        dealer.clearHand();
        dealer.addCard(shoe.draw());
        dealer.addCard(shoe.draw());
        dealer.calculateValue();
        // Dealer hits until at least 17
        while (dealer.getHandValue() < DEALER_STAND) {
            dealer.addCard(shoe.draw());
            dealer.calculateValue();
        }

        int dealerHV = dealer.getHandValue();

        // 2) Each active player plays two-card “hand” and outcome is decided
        for (Player p : players.values()) {
            if (!p.isActive()) continue;

            // Deal hand
            p.clearHand();
            p.addCard(shoe.draw());
            p.addCard(shoe.draw());
            int playerHV = calculateHandValue(p.getCards());
            p.setHandValue(playerHV);

            int bet = p.getCurrentBet();

            // 3) Determine result
            if (playerHV > 21) {
                // Player busts
                p.incrementLosses();
                p.setMoney(p.getMoney() - bet);
            } else if (dealerHV > 21) {
                // Dealer busts
                p.incrementWins();
                p.setMoney(p.getMoney() + bet);
            } else if (playerHV > dealerHV) {
                // Player higher
                p.incrementWins();
                p.setMoney(p.getMoney() + bet);
            } else if (playerHV < dealerHV) {
                // Dealer higher
                p.incrementLosses();
                p.setMoney(p.getMoney() - bet);
            }
            // else tie ⇒ push (no change)
        }
    }

    /** ───── Helpers ───── **/

    /**
     * Calculate blackjack hand value with Aces as 11 or 1.
     */
    private int calculateHandValue(List<Card> cards) {
        int sum = 0, aces = 0;
        for (Card c : cards) {
            String v = c.getValue();
            if ("J".equals(v) || "Q".equals(v) || "K".equals(v) || "10".equals(v)) {
                sum += 10;
            } else if ("A".equals(v)) {
                sum += 11;
                aces++;
            } else {
                sum += Integer.parseInt(v);
            }
        }
        // Downgrade Aces from 11 to 1 as needed
        while (sum > 21 && aces > 0) {
            sum -= 10;
            aces--;
        }
        return sum;
    }
}
