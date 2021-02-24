package com.example.java.maven.cardGamePeterPan;

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
    }
}
