package com.example.java.maven.cardGamePeterPan;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {


    @Test
    void shouldCreateDeckIncludingStandardCardsAndWitch() {

//        when
        Deck deck = new Deck();

//        then
        assertThat(deck.getCards().size()).isEqualTo(25); // because we have 24 card in small deck + Witch
    }
}
