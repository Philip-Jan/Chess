package com.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonHandler implements EventHandler<ActionEvent> {
    private final int number;
    private final Button button;
    private String ChessPiece;
    ButtonHandler(int number, Button button) {
        this.number = number;
        this.button = button;
        this.ChessPiece = "";
    }

    @Override
    public void handle(ActionEvent event) {
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