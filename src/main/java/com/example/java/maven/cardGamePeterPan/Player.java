package com.example.java.maven.cardGamePeterPan;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", with hand " + hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public int getHandSize() {
        return hand.size();
    }

    public void discardCardsFromHand(int firstCardIndex, int secondCardIndex) {
        hand.remove(secondCardIndex);
        hand.remove(firstCardIndex);
    }


    public boolean areCardsMatching(int firstCardIndex, int secondCardIndex) {
        Card firstCardToCompare = hand.get(firstCardIndex);
        Card secondCardToCompare = hand.get(secondCardIndex);

        return firstCardToCompare.isMatching(secondCardToCompare);
    }

    public void takeCardFromAnotherPlayersHand(Player player, int chosenCardIndex) {
        hand.add(player.hand.remove(chosenCardIndex));
    }

    public boolean isDiscardingCardsPossible() {
        for (Card card : hand) {
            int currentCardIndex = hand.indexOf(card);
            for (int i = (currentCardIndex+1); i < hand.size(); i++) {
                if (areCardsMatching(currentCardIndex, i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
