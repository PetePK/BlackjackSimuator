package com.blackjack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.model.Dealer;
import com.blackjack.service.GameService;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {

    private final GameService gameService;

    public DealerController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<Dealer> getDealer() {
        return ResponseEntity.ok(gameService.getGameState().getDealer());
    }
}
