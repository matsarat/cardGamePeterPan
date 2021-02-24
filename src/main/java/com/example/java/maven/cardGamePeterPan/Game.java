package com.example.java.maven.cardGamePeterPan;

import java.util.List;

public class Game {
    private final UserInputProvider userInputProvider;
    private final MessagePrinter messagePrinter;
    private final List<Player> playerList;
    private final Deck deck;



    public Game(UserInputProvider userInputProvider,
                MessagePrinter messagePrinter,
                List<Player> playerList, Deck deck) {
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
}
