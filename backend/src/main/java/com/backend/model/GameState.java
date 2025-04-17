package com.backend.model;

import java.util.List;

/**
 * Combines the list of players and the dealer info into one payload.
 */
public class GameState {
    private final List<Player> players;
    private final DealerInfo dealer;

    public GameState(List<Player> players, DealerInfo dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public DealerInfo getDealer() {
        return dealer;
    }
}
