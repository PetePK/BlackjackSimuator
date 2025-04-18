package com.blackjack.service;

import org.springframework.stereotype.Service;

import com.blackjack.model.Dealer;
import com.blackjack.model.GameState;

@Service
public class DealerService {

    private final GameState gameState;

    public DealerService(GameState gameState) {
        this.gameState = gameState;
    }

    public Dealer getDealer() {
        return gameState.getDealer();
    }
}
