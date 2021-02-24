package com.example.java.maven.cardGamePeterPan;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final ArrayList<Card>hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player " + name + hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }
}
