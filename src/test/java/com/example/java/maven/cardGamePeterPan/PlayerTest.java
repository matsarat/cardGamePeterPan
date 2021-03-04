package com.example.java.maven.cardGamePeterPan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;


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
}
