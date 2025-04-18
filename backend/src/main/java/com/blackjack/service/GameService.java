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

    private final GameState gameState;

    public GameService(GameState gameState) {
        this.gameState = gameState;
    }

    public void playRound() {
        Dealer dealer = gameState.getDealer();
        Deck deck = gameState.getDeck();
        List<Player> players = gameState.getPlayers();

        dealer.resetHand();
        players.forEach(Player::resetHand);

        dealInitialCards(deck, dealer, players);

        Card dealerUpCard = dealer.getHand().getCards().get(0);

        for (Player player : players) {
            if (!player.isActive()) continue;

            while (!player.getHand().isBust()
                    && shouldPlayerHit(player, dealerUpCard)
                    && player.getHand().getCards().size() < 5) {
                drawAndAddCard(deck, player);
            }
        }

        while (dealer.getHand().getValue() < 17) {
            drawAndAddCard(deck, dealer);
        }

        settleBets(players, dealer.getHand().getValue());
        gameState.incrementRound();
    }

    private void dealInitialCards(Deck deck, Dealer dealer, List<Player> players) {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                if (!player.isActive()) continue;
                drawAndAddCard(deck, player);
            }
            drawAndAddCard(deck, dealer);
        }
    }

    private void drawAndAddCard(Deck deck, Player player) {
        Card card = deck.draw();
        player.getHand().addCard(card);
        countCard(card);
    }

    private void drawAndAddCard(Deck deck, Dealer dealer) {
        Card card = deck.draw();
        dealer.getHand().addCard(card);
        countCard(card);
    }

    private boolean shouldPlayerHit(Player player, Card dealerUpCard) {
        return player.decideToHit(dealerUpCard, gameState);
    }

    private void settleBets(List<Player> players, int dealerValue) {
        for (Player player : players) {
            if (!player.isActive()) continue;

            int playerValue = player.getHand().getValue();

            if (player.getHand().isBust()) {
                player.lose(player.getCurrentBet());
                player.setRoundResult("loss");
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                player.win(player.getCurrentBet());
                player.setRoundResult("win");
            } else if (playerValue < dealerValue) {
                player.lose(player.getCurrentBet());
                player.setRoundResult("loss");
            } else {
                player.push();
                player.setRoundResult("push");
            }
        }
    }

    private void countCard(Card card) {
        String value = card.getValue();
        if (value.equals("2") || value.equals("3") || value.equals("4")
                || value.equals("5") || value.equals("6")) {
            gameState.incrementRunningCount(1);
        } else if (value.equals("10") || value.equals("J") || value.equals("Q")
                || value.equals("K") || value.equals("A")) {
            gameState.incrementRunningCount(-1);
        }
    }

    public void fastForward(int rounds, double speedInSeconds) {
        for (int i = 0; i < rounds; i++) {
            playRound();
            System.out.println("✅ Completed round " + gameState.getCurrentRound());

            if (speedInSeconds > 0) {
                try {
                    Thread.sleep((long) (speedInSeconds * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public void resetGame() {
        gameState.resetGame();
    }

    public GameState getGameState() {
        return gameState;
    }
}
