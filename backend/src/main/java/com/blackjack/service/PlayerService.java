package com.blackjack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blackjack.factory.PlayerFactory;
import com.blackjack.model.GameState;
import com.blackjack.model.player.Player;

@Service
public class PlayerService {

    private final GameState gameState;
    private final PlayerFactory factory;

    public PlayerService(GameState gameState, PlayerFactory factory) {
        this.gameState = gameState;
        this.factory = factory;
    }

    public Player addPlayer(String name, double money, String type) {
        Player player = factory.createPlayer(name, money, type);
        System.out.println("✅ ADDING PLAYER: " + player.getName() + " (" + type + ")");
        gameState.getPlayers().add(player); // 
        return player;
    }

    public Optional<Player> getPlayer(String id) {
        return gameState.getPlayers().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public boolean removePlayer(String id) {
        return gameState.getPlayers().removeIf(p -> p.getId().equals(id));
    }

    public List<Player> getAllPlayers() {
        return gameState.getPlayers();
    }

    public void toggleActive(String id) {
        getPlayer(id).ifPresent(p -> p.setActive(!p.isActive()));
    }
}
