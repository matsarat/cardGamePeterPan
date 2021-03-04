package com.example.java.maven.cardGamePeterPan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PlayerTest {
    Player player;

    @BeforeEach
    void createPlayer() {
        this.player = new Player("Janusz");
    }

    @Test
    void shouldAddCardToPlayersHand() {

//        given
        Card card = Mockito.mock(Card.class);

//        when
        player.addCardToHand(card);

//        then
        assertThat(player.getHandSize()).isEqualTo(1);
    }

    @Test
    void shouldTakeCardFromAnotherPlayersHand() {

//        given
        Player anotherPlayer = new Player("Another");
        Card card = Mockito.mock(Card.class);
        anotherPlayer.addCardToHand(card);

//        when

        player.takeCardFromAnotherPlayersHand(anotherPlayer, 0);

//        then

        assertThat(player.getHandSize()).isEqualTo(1);
        assertThat(anotherPlayer.getHandSize()).isEqualTo(0);

    }

    @Test
    void shouldThrowExceptionIfChosenToTakeCardIndexIsOutOfBounds() {

//        given
        Player anotherPlayer = new Player("Another");
        Card card = Mockito.mock(Card.class);
        anotherPlayer.addCardToHand(card);
        int cardIndexOutOfBounds = 1;

//        then
        assertThrows(IndexOutOfBoundsException.class, () -> player.takeCardFromAnotherPlayersHand(anotherPlayer, cardIndexOutOfBounds));
    }

    @Test
    void shouldReturnTrueIfPlayerHasTwoCardsMatchingBySuit() {

//        given
        Card card1 = new Card(Card.Suit.CLUB, Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.SPADE, Card.Rank.JACK);
        Card card3 = new Card(Card.Suit.CLUB, Card.Rank.TEN);

        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);

//        then
        assertThat(player.isDiscardingCardsPossible()).isTrue();
    }

    @Test
    void shouldReturnTrueIfPlayerHasTwoCardsMatchingByRank() {

//        given
        Card card1 = new Card(Card.Suit.CLUB, Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.SPADE, Card.Rank.JACK);
        Card card3 = new Card(Card.Suit.HEART, Card.Rank.ACE);

        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);

//        then
        assertThat(player.isDiscardingCardsPossible()).isTrue();
    }
}
