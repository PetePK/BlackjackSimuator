package com.blackjack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blackjack.model.Card;
import com.blackjack.model.Dealer;
import com.blackjack.model.Deck;
import com.blackjack.model.GameState;
import com.blackjack.model.player.Player;

@Service
public class GameService {

    private final GameState gameState; // ✅ Injected, not manually created

    public GameService(GameState gameState) {
        this.gameState = gameState;
    }

    public void playRound() {
        Deck deck = gameState.getDeck();
        Dealer dealer = gameState.getDealer();
        List<Player> players = gameState.getPlayers();

        dealer.resetHand();
        players.forEach(Player::resetHand);

        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                if (player.isActive()) {
                    player.getHand().addCard(deck.draw());
                }
            }
            dealer.getHand().addCard(deck.draw());
        }

        Card dealerUpCard = dealer.getHand().getCards().get(0);

        for (Player player : players) {
            if (!player.isActive()) continue;
            while (!player.getHand().isBust() &&
                   player.decideToHit(dealerUpCard, deck.getCardCount())) {
                player.getHand().addCard(deck.draw());
            }
        }

        while (dealer.getHand().getValue() < 17) {
            dealer.getHand().addCard(deck.draw());
        }

        int dealerValue = dealer.getHand().getValue();

        for (Player player : players) {
            if (!player.isActive()) continue;
            int playerValue = player.getHand().getValue();
            if (player.getHand().isBust()) {
                player.lose(player.getCurrentBet());
            } else if (dealer.getHand().isBust() || playerValue > dealerValue) {
                player.win(player.getCurrentBet());
            } else if (playerValue < dealerValue) {
                player.lose(player.getCurrentBet());
            } else {
                player.push();
            }
        }

        gameState.incrementRound();
    }

    public void resetGame() {
        gameState.resetGame();
    }

    public GameState getGameState() {
        return gameState;
    }
}
