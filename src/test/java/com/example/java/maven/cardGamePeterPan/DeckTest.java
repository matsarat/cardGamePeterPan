package com.example.java.maven.cardGamePeterPan;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    private static Deck deck;

    @BeforeAll
    public static void createDeck() {
        deck = new Deck();
    }

    @Test
    void deckShouldIncludeRequiredNumberOfCards() {

//        then
        assertThat(deck.getCards().size()).isEqualTo(25); // because we have 24 card in small deck (from 9 to Ace) + Witch
    }

    @Test
    void shouldTakeCardFromDeck() {

//        when
        deck.takeCard();

//        then
        assertThat(deck.getCards().size()).isEqualTo(24);
    }
}
