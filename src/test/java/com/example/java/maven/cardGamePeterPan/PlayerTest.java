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
        assertThrows(IndexOutOfBoundsException.class, () -> player.takeCardFromAnotherPlayersHand(anotherPlayer, 1));
    }
}
