package com.backend.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.backend.model.Card;
import com.backend.model.Deck;

public class Shoe {
    private final List<Card> cards = new ArrayList<>();
    public Shoe(int decks) {
        for (int i = 0; i < decks; i++) cards.addAll(Deck.createStandardDeck());
        Collections.shuffle(cards);
    }
    public Card draw() {
        if (cards.isEmpty()) throw new IllegalStateException("Shoe is empty");
        return cards.remove(0);
    }
}
