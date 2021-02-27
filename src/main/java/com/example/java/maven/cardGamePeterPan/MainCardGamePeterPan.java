package com.example.java.maven.cardGamePeterPan;

import java.util.ArrayList;

public class MainCardGamePeterPan {

    public static void main(String[] args) {

        int numberOfPlayers = 3;
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


        int activePlayers = game.countActivePlayers();
        while (activePlayers > 1) {
            for (Player player : players) {
                if (player.isPlaying()) {
                    messagePrinter.printMessage(String.format("Your turn, %s", player.getName()));
                    game.getCardFromPreviousPlayerHand(player);
                    if (player.isDiscardingCardsPossible()) {
                        game.choseCardsToDiscard(player);
                    }
                    player.checkIfWon();
                    activePlayers = game.countActivePlayers();
                }
            }
        }
    }
}
