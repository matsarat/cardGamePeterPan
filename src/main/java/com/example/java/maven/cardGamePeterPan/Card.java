package com.example.java.maven.cardGamePeterPan;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private final Suit suit;
    private final Rank rank;


    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return suit.symbol + rank.figure;
    }

    public enum Suit {

        CLUB("♣"),
        DIAMOND("♦"),
        HEART("♥"),
        SPADE("♠"),
        WITCH("");

        private final String symbol;

        Suit(String symbol) {
            this.symbol = symbol;
        }

        public static List<Suit> getSuitsWithoutWitch() {
            List<Suit> suitsWithoutWitch = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                if (suit != WITCH) {
                    suitsWithoutWitch.add(suit);
                }
            }
            return suitsWithoutWitch;
        }
    }

    public enum Rank {

        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A"),
        WITCH("WITCH");

        private final String figure;

        Rank(String rank) {
            this.figure = rank;
        }

        public static List<Rank> getRanksWithoutWitch() {
            List<Rank> ranksWithoutWitch = new ArrayList<>();
            for (Rank rank : Rank.values()) {
                if (rank != WITCH) {
                    ranksWithoutWitch.add(rank);
                }
            }
            return ranksWithoutWitch;
        }
    }
}
