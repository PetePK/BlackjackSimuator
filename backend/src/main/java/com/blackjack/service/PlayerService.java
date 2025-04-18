package com.blackjack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blackjack.factory.PlayerFactory;
import com.blackjack.model.GameState;
import com.blackjack.model.player.CardCounterPlayer;
import com.blackjack.model.player.Player;

@Service
public class PlayerService {

    private final GameState gameState;
    private final PlayerFactory playerFactory;

    public PlayerService(GameState gameState, PlayerFactory playerFactory) {
        this.gameState = gameState;
        this.playerFactory = playerFactory;
    }

    public Player addPlayer(String name, double money, String type) {
        Player player = playerFactory.createPlayer(name, money, type);
        System.out.println("ADDING PLAYER: " + player.getName() + " (" + type + ")");
        gameState.getPlayers().add(player);
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

    public void resetAllPlayers() {
        for (Player player : gameState.getPlayers()) {
            resetPlayerDefaults(player);
        }
    }

    public void updatePlayer(String id, String name, double money, String playerType) {
        Optional<Player> optional = getPlayer(id);
        if (optional.isEmpty()) return;

        Player player = optional.get();
        player.setName(name);
        player.setMoney(money);

        // Dynamically update strategy
        Player updated = playerFactory.createPlayer(name, money, playerType);
        player.setStrategy(updated.getStrategy());
    }

    private void resetPlayerDefaults(Player player) {
        player.setMoney(2000);
        player.setCurrentBet(10);
        player.resetHand();
        player.resetRecord();
        player.setActive(true);

        if (player instanceof CardCounterPlayer counter) {
            counter.resetCount();
        }
    }
}
