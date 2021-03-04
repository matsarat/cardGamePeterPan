package com.example.java.maven.cardGamePeterPan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

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

    @Test
    void shouldPrintPlayers() {

//        when
        game.printPlayers();

//        then

        then(messagePrinter)
                .should(times(1))
                .printPlayer(playerList.get(0));
        then(messagePrinter)
                .should(times(1))
                .printPlayer(playerList.get(1));
        then(messagePrinter)
                .should(times(1))
                .printPlayer(playerList.get(2));
    }

    @Test
    void shouldDiscardCards() {

//        given
        Player player = playerList.get(0);
        Card card1 = new Card(Card.Suit.CLUB, Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.CLUB, Card.Rank.JACK);
        Card card3 = new Card(Card.Suit.SPADE, Card.Rank.TEN);
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);

        given(userInputProvider.getNumberOfChosenCard())
                .willReturn(0).willReturn(1);

//        when
        game.discardCards(player);

//        then
        then(messagePrinter)
                .should(times(1))
                .printPlayer(player);
        then(messagePrinter)
                .should(times(1))
                .printMessage(ASK_FOR_CARDS_TO_DISCARD);
        then(messagePrinter)
                .should(times(1))
                .printMessage(ASK_FOR_NEXT_CARD_TO_DISCARD);


        assertThat(player.getHandSize()).isEqualTo(1);
        assertThat(player.getHand().get(0)).isEqualTo(card3);

    }

    @Test
    void shouldTakeChosenCardFromPreviousPlayerHand() {

//        given

        Player player = playerList.get(0);

        Card card1 = new Card(Card.Suit.CLUB, Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.CLUB, Card.Rank.JACK);
        Card card3 = new Card(Card.Suit.SPADE, Card.Rank.TEN);
        playerList.get(1).addCardToHand(card1);// to make sure that all players are active and code gets real previous, not only available player
        playerList.get(2).addCardToHand(card2);
        playerList.get(2).addCardToHand(card3);

        given(userInputProvider.getNumberOfChosenCard())
                .willReturn(1);

//        when
        game.getCardFromPreviousPlayerHand(player);

//        then

        assertThat(player.getHandSize()).isEqualTo(1);
        assertThat(player.getHand().get(0)).isEqualTo(card3);
        assertThat(playerList.get(2).getHandSize()).isEqualTo(1);
    }

    @Test
    void shouldReturnListOfActivePlayers() {

//        given
        game.dealCards();

//        then
        assertThat(game.getActivePlayers().size()).isEqualTo(3);
    }

    
}
