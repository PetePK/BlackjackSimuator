package com.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.DealerInfo;
import com.backend.model.GameState;
import com.backend.model.Player;
import com.backend.service.GameService;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /** Fetch all players */
    @GetMapping("/players")
    public List<Player> getPlayers() {
        return gameService.getAllPlayers();
    }

    /** Add a new player */
    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        return gameService.addPlayer(player);
    }

    /** Remove a player by ID */
    @DeleteMapping("/players/{id}")
    public void removePlayer(@PathVariable String id) {
        gameService.removePlayer(id);
    }

    /** Update an existing player */
    @PutMapping("/players")
    public Player updatePlayer(@RequestBody Player player) {
        return gameService.updatePlayer(player);
    }

    /** Toggle active/inactive on a player */
    @PutMapping("/players/{id}/toggle")
    public Player togglePlayerActive(@PathVariable String id) {
        gameService.togglePlayerActive(id);
        // Return the updated player
        return gameService.getAllPlayers().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    /** Reset all players to defaults */
    @PostMapping("/reset")
    public void resetAll() {
        gameService.resetAllPlayers();
    }

    /** Play one round of blackjack */
    @PostMapping("/round")
    public void playRound() {
        gameService.playRound();
    }

    /** Shuffle / reset the shoe and dealer hand */
    @PostMapping("/shuffle")
    public void shuffleDeck() {
        gameService.shuffleDeck();
    }

    /** Get current dealer hand & value */
    @GetMapping("/dealer")
    public DealerInfo getDealer() {
        return gameService.getDealerInfo();
    }

    /** Get full game state: players + dealer */
    @GetMapping("/state")
    public GameState getState() {
        return new GameState(
            gameService.getAllPlayers(),
            gameService.getDealerInfo()
        );
    }
}
