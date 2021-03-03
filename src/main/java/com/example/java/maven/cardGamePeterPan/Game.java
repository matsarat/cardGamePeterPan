package com.example.java.maven.cardGamePeterPan;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final UserInputProvider userInputProvider;
    private final MessagePrinter messagePrinter;
    private final List<Player> playerList;
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
                List<Player> playerList,
                Deck deck) {
        this.userInputProvider = userInputProvider;
        this.messagePrinter = messagePrinter;
        this.playerList = playerList;
        this.deck = deck;
    }

    public void dealCards() {
        while(deck.getCards().size() > 0) {
            for (Player player : playerList) {
                if (deck.getCards().size() > 0) {
                    player.addCardToHand(deck.takeCard());
                }
            }
        }
    }

    public void printPlayers() {
        for (Player player : playerList) {
            messagePrinter.printPlayer(player);
        }
    }

    private int getCardIndex(String message){
        messagePrinter.printMessage(message);
        return userInputProvider.getNumberOfChosenCard();
    }

    public void discardCards(Player player) {
        messagePrinter.printPlayer(player);

        int firstCardToDiscardIndex = getCardIndex(ASK_FOR_CARDS_TO_DISCARD);
        int secondCardToDiscardIndex = getCardIndex(ASK_FOR_NEXT_CARD_TO_DISCARD);

        try {
            if (player.areCardsMatching(firstCardToDiscardIndex, secondCardToDiscardIndex)) {
                player.discardCardsFromHand(firstCardToDiscardIndex, secondCardToDiscardIndex);
            } else {
                messagePrinter.printError(WRONG_CARDS_TO_DISCARD);
                discardCards(player);
            }
        } catch (IndexOutOfBoundsException exception) {
            messagePrinter.printError(exception.getMessage());
            discardCards(player);
        }
    }

    public void getCardFromPreviousPlayerHand(Player player) {
        Player previousPlayer = getPreviousPlayer(player);
        messagePrinter.printMessage(String.format("%s has %s cards. Which one would you like to draw?", previousPlayer.getName(), previousPlayer.getHandSize()));
        try {
            player.takeCardFromAnotherPlayersHand(previousPlayer, userInputProvider.getNumberOfChosenCard());
        }
        catch (IndexOutOfBoundsException exception) {
            messagePrinter.printError(exception.getMessage());
            getCardFromPreviousPlayerHand(player);
        }
    }

    public Player getPreviousPlayer(Player player) {
        List<Player> activePlayers = getActivePlayers();
        int indexOfCurrentPlayer = activePlayers.indexOf(player);
        if ((indexOfCurrentPlayer - 1) < 0) {
            return activePlayers.get(activePlayers.size() - 1);
        }
        else {
            return activePlayers.get(indexOfCurrentPlayer-1);
        }
    }

    public int countActivePlayers() {
        return getActivePlayers().size();
    }

    public List<Player> getActivePlayers(){
        return playerList.stream()
                .filter(player -> player.getHandSize() > 0)
                .collect(Collectors.toList());
    }

    public boolean checkIfPlayerIsActive(Player player) {
        return player.getHandSize() != 0;
    }

    public void play() {
        while (countActivePlayers() > 1) {
            for (Player player : getActivePlayers()) {
                if (checkIfPlayerIsActive(player)) {
                    messagePrinter.printMessage(String.format("Your turn, %s", player.getName()));
                    getCardFromPreviousPlayerHand(player);
                    if (player.isDiscardingCardsPossible()) {
                        discardCards(player);
                    }
                }
            }
        }
    }
}
