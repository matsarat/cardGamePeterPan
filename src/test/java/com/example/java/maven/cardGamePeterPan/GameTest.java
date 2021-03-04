package com.example.java.maven.cardGamePeterPan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private final static String ASK_FOR_CARDS_TO_DISCARD = """
            Which cards would you like to discard? 
            Insert number telling which chosen card is, counting from zero:""";
    private final static String ASK_FOR_NEXT_CARD_TO_DISCARD = "Which else?";
    private final static String WRONG_CARDS_TO_DISCARD = """
            "You can't do that! 
            You can discard two cards of the same colour or with the same figure!""";
    Game game;
    UserInputProvider userInputProvider = Mockito.mock(UserInputProvider.class);
    MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
    List<Player> playerList = List.of(new Player("Janusz"), new Player("Grażyna"), new Player("Seba"));
    Deck deck = new Deck();


    @BeforeEach
    void setUp() {
        this.game = new Game(userInputProvider, messagePrinter, playerList, deck);
    }

    @Test
    void shouldDealCards() {

//        when
        game.dealCards();

//        then
        assertThat(playerList.get(0).getHandSize()).isEqualTo(9); // because dealing starts from first player on the list and number of cards is odd
        assertThat(playerList.get(1).getHandSize()).isEqualTo(8);
        assertThat(playerList.get(2).getHandSize()).isEqualTo(8);
        assertThat(deck.getCards().size()).isEqualTo(0);
    }


}
