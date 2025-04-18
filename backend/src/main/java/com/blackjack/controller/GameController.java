package com.blackjack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.dto.SimulationRequest;
import com.blackjack.model.GameState;
import com.blackjack.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Plays a single round of Blackjack.
     */
    @PostMapping("/round/play")
    public ResponseEntity<GameState> playRound() {
        gameService.playRound();
        return ResponseEntity.ok(gameService.getGameState());
    }

    /**
     * Resets the entire game state: players, deck, round, and dealer hand.
     */
    @PostMapping("/reset")
    public ResponseEntity<GameState> resetGame() {
        gameService.resetGame();
        return ResponseEntity.ok(gameService.getGameState());
    }

    /**
     * Returns the current state of the game (players, dealer, deck, etc.)
     */
    @GetMapping("/state")
    public ResponseEntity<GameState> getGameState() {
        return ResponseEntity.ok(gameService.getGameState());
    }

    /**
     * Simulates multiple rounds with an optional delay between them.
     */
    @PostMapping("/simulation/fastForward")
    public ResponseEntity<GameState> fastForward(@RequestBody SimulationRequest request) {
        gameService.fastForward(request.getRounds(), request.getSpeed());
        return ResponseEntity.ok(gameService.getGameState());
    }
}
