package com.blackjack.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.model.player.Player;
import com.blackjack.service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        double money = ((Number) payload.get("money")).doubleValue();
        String type = (String) payload.get("playerType");

        Player player = playerService.addPlayer(name, money, type);
        return ResponseEntity.ok(player);
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetAllPlayers() {
        playerService.resetAllPlayers();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable String id) {
        Optional<Player> player = playerService.getPlayer(id);
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePlayer(@PathVariable String id) {
        boolean removed = playerService.removePlayer(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<Void> toggleActive(@PathVariable String id) {
        playerService.toggleActive(id);
        return ResponseEntity.ok().build();
    }
}
