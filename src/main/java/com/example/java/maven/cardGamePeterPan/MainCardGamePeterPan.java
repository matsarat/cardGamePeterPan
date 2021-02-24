package com.example.java.maven.cardGamePeterPan;

import java.util.ArrayList;
import java.util.List;

public class MainCardGamePeterPan {

    public static void main(String[] args) {
        int numberOfPlayers = 2;
        MessagePrinter messagePrinter = new MessagePrinter();
        UserInputProvider userInputProvider = new UserInputProvider(messagePrinter);

        ArrayList<Player> players = new ArrayList<>();
        Deck deck = new Deck();

        for (int i = 1; i <= numberOfPlayers; i++) {
            messagePrinter.printMessage("Player " + i);
            players.add(new Player(userInputProvider.getPlayersName()));
        }

        Game game = new Game(userInputProvider,
                messagePrinter,
                players,
                deck);


        game.dealCards();
        game.printPlayers();
        players.get(0).matchCardBySuit();
        game.printPlayers();
    }
}
