package com.example.java.maven.cardGamePeterPan;

import java.util.Scanner;

public class MessagePrinter {

    public void printMessage(String messageToPrint) {
        System.out.println(messageToPrint);
    }

    public void printError(String errorToPrint){
        System.err.println(errorToPrint);
    }

    public void printPlayer(Player player) {
        System.out.println(player);
    }



}
