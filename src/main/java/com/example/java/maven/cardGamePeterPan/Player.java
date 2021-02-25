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
        return name + ", with hand " + hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void discardCardsFromHand(int firstCardIndex, int secondCardIndex) {
        hand.remove(firstCardIndex);
        hand.remove(secondCardIndex);
    }

    public boolean areCardsMatchingBySuit(int firstCardIndex, int secondCardIndex) {
        Card firstCardToCompare = hand.get(firstCardIndex);
        Card secondCardToCompare = hand.get(secondCardIndex);
        return firstCardToCompare.getSuit().equals(secondCardToCompare.getSuit());
    }

    public boolean areCardsMatchingByRank(int firstCardIndex, int secondCardIndex) {
        Card firstCardToCompare = hand.get(firstCardIndex);
        Card secondCardToCompare = hand.get(secondCardIndex);
        return firstCardToCompare.getRank().equals(secondCardToCompare.getRank());
    }

    public void takeCardFromPlayersHand(Player player, int numberOfChosenCard) {
        hand.add(player.hand.remove(numberOfChosenCard - 1));
    }
}
