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
        return "Player " + name + " " + hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void matchCardBySuit() {
        for (Card card : hand) {
            int startMatchingFromIndex = hand.indexOf(card);
            for (int i = startMatchingFromIndex + 1; i < hand.size(); i++) {
                if (card.getSuit().equals(hand.get(i).getSuit())) {
                    hand.remove(card);
                    hand.remove(hand.get(i));
                }
            }
            break;
        }
    }

    public void takeCardFromPlayersHand(Player player, int numberOfChosenCard) {
        hand.add(player.hand.remove(numberOfChosenCard - 1));
    }
}
