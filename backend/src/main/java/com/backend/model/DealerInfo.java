package com.backend.model;
import java.util.ArrayList;
import java.util.List;

public class DealerInfo {
    private final List<Card> cards = new ArrayList<>();
    private int handValue;
    public void addCard(Card c) { cards.add(c); }
    public void clearHand() { cards.clear(); handValue = 0; }
    public List<Card> getCards() { return cards; }
    public int getHandValue() { return handValue; }
    public void calculateValue() {
        int sum = 0, aces = 0;
        for (Card c : cards) {
            String v = c.getValue();
            if ("J".equals(v)||"Q".equals(v)||"K".equals(v)||"10".equals(v)) sum += 10;
            else if ("A".equals(v)) { sum += 11; aces++; }
            else sum += Integer.parseInt(v);
        }
        while (sum > 21 && aces-- > 0) sum -= 10;
        handValue = sum;
    }
}
