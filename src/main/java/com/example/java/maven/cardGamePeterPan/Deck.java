package com.example.java.maven.cardGamePeterPan;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Stack<Card> getCards() {
        Stack<Card> cardsCopy = new Stack<>();
        cardsCopy.addAll(cards);
        return cardsCopy;
    }

    public Deck() {
        this.cards = new Stack<>();
        for (Card.Suit suit : Card.Suit.values()) {
            if (suit != Card.Suit.WITCH)
                for (Card.Rank rank : Card.Rank.values()) {
                    if (rank != Card.Rank.WITCH) {
                        Card card = new Card(suit, rank);
                        cards.push(card);
                    }
                }
        }
        cards.push(new Card(Card.Suit.WITCH, Card.Rank.WITCH));
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        return "Deck:" + cards;
    }

    public Card takeCardFromDeck() {
        return cards.pop();
    }
}
