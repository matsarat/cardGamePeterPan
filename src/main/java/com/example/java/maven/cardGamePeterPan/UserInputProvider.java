package com.example.java.maven.cardGamePeterPan;

import java.util.Scanner;

public class UserInputProvider {
    private final MessagePrinter messagePrinter;
    private final static String ASK_FOR_NAME = "What is your name? ";
    private final static String ASK_FOR_CARD = "Which card would you like to draw?";

    public UserInputProvider(MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }

    public String getPlayersName() {
        messagePrinter.printMessage(ASK_FOR_NAME);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getNumberOfChosenCard() {
        messagePrinter.printMessage(ASK_FOR_CARD);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
