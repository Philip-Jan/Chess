package com.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonHandler implements EventHandler<ActionEvent> {
    private final int number;
    private String ChessPiece;

    ButtonHandler(int number) {
        this.number = number;
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        button.getGraphic();
        switch (ChessPiece) {
            case "WK":
                break;
            case "WD":
                break;
            case "WT":
                break;
            case "WP": //Paard
                break;
            case "WL":
                break;
            case "Wp": //pion
                break;
            case "ZK":
                break;
            case "ZD":
                break;
            case "ZT":
                break;
            case "ZP":
                break;
            case "ZL":
                break;
            case "Zp":
                break;
            default:
        }
    }

}