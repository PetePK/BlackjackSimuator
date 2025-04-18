package com.blackjack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.model.Dealer;
import com.blackjack.model.GameState;
import com.blackjack.service.DealerService;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {
    private final DealerService dealerService;
    private final GameState gameState;

    public DealerController(DealerService dealerService, GameState gameState) {
        this.dealerService = dealerService;
        this.gameState = gameState;
    }

    @GetMapping
    public ResponseEntity<Dealer> getDealer() {
        return ResponseEntity.ok(dealerService.getDealer());
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetHand() {
        dealerService.resetHand();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/shuffle")
    public ResponseEntity<Void> shuffleDeck() {
        gameState.getDeck().shuffle();         // Refill and shuffle deck
        gameState.resetRunningCount();         // Reset card counter
        return ResponseEntity.ok().build();
    }
}
