package com.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonHandler implements EventHandler<ActionEvent> {
    private final int number;
    private final Board board;
    private char ChessPiece;

    ButtonHandler(int number, Board board) {
        this.number = number;
        this.board = board;
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        button.getGraphic();
        switch (ChessPiece) {
            case 'K':
                break;
            case 'Q':
                break;
            case 'R':
                break;
            case 'N': //Paard
                break;
            case 'B':
                break;
            case 'P': //pion
                break;
            case 'k':
                break;
            case 'q':
                break;
            case 'r':
                break;
            case 'n':
                break;
            case 'b':
                break;
            case 'p':
                break;
            default:
        }
    }

}