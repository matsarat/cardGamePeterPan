package com.example.java.maven.cardGamePeterPan;

import java.util.Scanner;

public class UserInputProvider {
    private final MessagePrinter messagePrinter;
    private final static String ASK_FOR_NAME = "What is your name? ";
    private final static String INPUT_MUST_BE_AN_INTEGER = "Your input must be an integer! Try again!";

    public UserInputProvider(MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }

    public String getPlayersName() {
        messagePrinter.printMessage(ASK_FOR_NAME);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getNumberOfChosenCard() {
        Scanner scanner = new Scanner(System.in);
        try {
            int numberOfChosenCard = Integer.parseInt(scanner.nextLine());
            return numberOfChosenCard;
        }catch(NumberFormatException e) {
            messagePrinter.printError(INPUT_MUST_BE_AN_INTEGER);
            return getNumberOfChosenCard();
        }
    }
}
