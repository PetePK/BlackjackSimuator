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

    @PostMapping("/round/play")
    public ResponseEntity<GameState> playRound() {
        gameService.playRound();
        return ResponseEntity.ok(gameService.getGameState());
    }

    @PostMapping("/reset")
    public ResponseEntity<GameState> resetGame() {
        gameService.resetGame();
        return ResponseEntity.ok(gameService.getGameState());
    }

    @GetMapping("/state")
    public ResponseEntity<GameState> getState() {
        return ResponseEntity.ok(gameService.getGameState());
    }

    @PostMapping("/simulation/fastForward")
    public ResponseEntity<GameState> fastForward(@RequestBody SimulationRequest request) {
        gameService.fastForward(request.getRounds(), request.getSpeed());
        return ResponseEntity.ok(gameService.getGameState());
    }
}
