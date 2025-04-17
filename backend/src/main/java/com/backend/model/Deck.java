package com.backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    /**
     * Creates and shuffles one standard 52‑card deck.
     */
    public static List<Card> createStandardDeck() {
        String[] suits = {"hearts","diamonds","clubs","spades"};
        String[] vals = {
            "A","2","3","4","5","6","7","8","9","10","J","Q","K"
        };
        List<Card> deck = new ArrayList<>();
        for (String s : suits) {
            for (String v : vals) {
                deck.add(new Card(s, v));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }
}
