package com.blackjack.factory;

import org.springframework.stereotype.Component;

import com.blackjack.model.player.AggressivePlayer;
import com.blackjack.model.player.CardCounterPlayer;
import com.blackjack.model.player.DefaultPlayer;
import com.blackjack.model.player.OptimalPlayer;
import com.blackjack.model.player.PassivePlayer;
import com.blackjack.model.player.Player;

@Component
public class PlayerFactory {
    public Player createPlayer(String name, double money, String type) {
        if (type == null) {
            type = "default";
        }
        return switch (type.toLowerCase()) {
            case "aggressive" -> new AggressivePlayer(name, money);
            case "passive" -> new PassivePlayer(name, money);
            case "optimal", "tactician", "strategist" -> new OptimalPlayer(name, money);
            case "card counter", "counter" -> new CardCounterPlayer(name, money);
            default -> new DefaultPlayer(name, money);
        };
    }
}
