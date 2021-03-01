package com.example.java.maven.cardGamePeterPan;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final ArrayList<Card>hand;
    private boolean isPlaying;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.isPlaying = true;
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
        hand.remove(secondCardIndex);
        hand.remove(firstCardIndex);
    }


    public boolean areCardsMatching(int firstCardIndex, int secondCardIndex) {
        Card firstCardToCompare = hand.get(firstCardIndex);
        Card secondCardToCompare = hand.get(secondCardIndex);

        if (firstCardToCompare.getSuit().equals(secondCardToCompare.getSuit())) {
            return true;
        }
        else if (firstCardToCompare.getRank().equals(secondCardToCompare.getRank())) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void takeCardFromAnotherPlayersHand(Player player, int chosenCardIndex) {
        hand.add(player.hand.remove(chosenCardIndex));
    }

    public boolean isDiscardingCardsPossible() {
        for (Card card : hand) {
            for (int i = (hand.indexOf(card)+1); i < hand.size(); i++) {
                if (card.getSuit().equals(hand.get(i).getSuit())) {
                    return true;
                }
                else if (card.getRank().equals(hand.get(i).getRank())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean checkIfWon() {
        if (hand.size() == 0) {
            setPlaying(false);
            return true;
        }
        else {
            return false;
        }
    }
}
