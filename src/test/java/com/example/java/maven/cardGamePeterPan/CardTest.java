package com.example.java.maven.cardGamePeterPan;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    void shouldReturnListOfCardSuitsWithoutWitch() {

//        when
        Card.Suit.getSuitsWithoutWitch();

//        then
        assertThat(Card.Suit.getSuitsWithoutWitch().size()).isEqualTo(4); // because we have 4 standard card suits
    }

    @Test
    void shouldReturnListOfCardRanksWithoutWitch() {

//        when
        Card.Rank.getRanksWithoutWitch();

//        then
        assertThat(Card.Rank.getRanksWithoutWitch().size()).isEqualTo(6); // because we have 6 ranks in small deck (from 9 to Ace)
    }
    @Test
    void shouldReturnTrueIfCardsAreMatchingBySuitOrRank() {

//        given
        Card card1 = new Card(Card.Suit.CLUB, Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.CLUB, Card.Rank.TEN);
        Card card3 = new Card(Card.Suit.HEART, Card.Rank.ACE);
        Card card4 = new Card(Card.Suit.SPADE, Card.Rank.JACK);

//        then
        assertThat(card1.isMatching(card2)).isTrue();
        assertThat(card1.isMatching(card3)).isTrue();
        assertThat(card1.isMatching(card4)).isFalse();
    }
}
