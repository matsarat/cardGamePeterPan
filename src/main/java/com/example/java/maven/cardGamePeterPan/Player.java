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

//    TODO what if player is a monkey?
    public void discardCardsFromHand(int firstCardIndex, int secondCardIndex) {
        validateCardIndex(firstCardIndex);
        validateCardIndex(secondCardIndex);
        hand.remove(secondCardIndex);
        hand.remove(firstCardIndex);
    }


    public boolean areCardsMatching(int firstCardIndex, int secondCardIndex) {
        validateCardIndex(firstCardIndex);
        validateCardIndex(secondCardIndex);
        Card firstCardToCompare = hand.get(firstCardIndex);
        Card secondCardToCompare = hand.get(secondCardIndex);
        return firstCardToCompare.isMatching(secondCardToCompare);
    }

    public void takeCardFromAnotherPlayersHand(Player player, int userInput) {
        player.validateCardIndex(userInput);
        hand.add(player.hand.remove(userInput));
    }

    private void validateCardIndex(int userInput) {
        if (userInput < 0 || userInput >= hand.size()) {
            throw new IndexOutOfBoundsException(String.format("Your input must be an integer between 0 and %s", hand.size()-1));
        }
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
