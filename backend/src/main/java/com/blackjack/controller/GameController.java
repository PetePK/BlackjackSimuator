
package com.blackjack.controller;

import com.blackjack.model.GameState;
import com.blackjack.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
