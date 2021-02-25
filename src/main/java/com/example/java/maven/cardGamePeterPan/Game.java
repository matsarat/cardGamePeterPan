package com.example.java.maven.cardGamePeterPan;

import java.util.ArrayList;

public class Game {
    private final UserInputProvider userInputProvider;
    private final MessagePrinter messagePrinter;
    private final ArrayList<Player> playerList;
    private final Deck deck;

    private final static String ASK_FOR_CARDS_TO_DISCARD= """
            Which cards would you like to discard? 
            Insert number telling which chosen card is, counting from left:""";
    private final static String ASK_FOR_NEXT_CARD_TO_DISCARD = "Which else?";
    private final static String WRONG_CARDS_TO_DISCARD = """
            "You can't do that! 
            You can discard two cards of the same colour or with the same figure!""";




    public Game(UserInputProvider userInputProvider,
                MessagePrinter messagePrinter,
                ArrayList<Player> playerList, Deck deck) {
        this.userInputProvider = userInputProvider;
        this.messagePrinter = messagePrinter;
        this.playerList = playerList;
        this.deck = deck;
    }

    public void dealCards() {
        boolean areCards = true;
        while (areCards) {
            for (Player player : playerList) {
                if (deck.getCards().size() > 0) {
                    player.addCardToHand(deck.takeCardFromDeck());
                }
                if (deck.getCards().size() == 0) {
                    areCards = false;
                }
            }
        }
    }

    public void printPlayers() {
        for (Player player : playerList) {
            messagePrinter.printPlayer(player);
        }
    }

    public void choseCardsToDiscard(Player player) {
        messagePrinter.printMessage(ASK_FOR_CARDS_TO_DISCARD);
        int firstCardToDiscardIndex = (getValidCardIndex(player));
        messagePrinter.printMessage(ASK_FOR_NEXT_CARD_TO_DISCARD);
        int secondCardToDiscardIndex = (getValidCardIndex(player));

        discardCardsIfPossible(player, firstCardToDiscardIndex, secondCardToDiscardIndex);
    }

    private void discardCardsIfPossible(Player player, int firstCardToDiscardIndex, int secondCardToDiscardIndex) {
        if (player.areCardsMatchingByRank(firstCardToDiscardIndex, secondCardToDiscardIndex)) {
            player.discardCardsFromHand(firstCardToDiscardIndex, secondCardToDiscardIndex);
        }
        else if (player.areCardsMatchingBySuit(firstCardToDiscardIndex, secondCardToDiscardIndex)) {
            player.discardCardsFromHand(firstCardToDiscardIndex, secondCardToDiscardIndex);
        }
        else {
            messagePrinter.printError(WRONG_CARDS_TO_DISCARD);
            choseCardsToDiscard(player);
        }
    }

    public int getValidCardIndex(Player player) {
        int userInput = userInputProvider.getNumberOfChosenCard();
        if (userInput > 0 && userInput <= player.getHand().size()) {
            return (userInput - 1);
        }
        else {
            messagePrinter.printError(String.format("Your input must be an integer between 1 and %s", player.getHand().size()));
            return getValidCardIndex(player);
        }
    }
}
