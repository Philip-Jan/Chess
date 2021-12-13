package com.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonHandler implements EventHandler<ActionEvent> {
    private final int number;
    private final Board board;
    private final char ChessPiece;

    ButtonHandler(int number, char ChessPiece, Board board) {
        this.number = number;
        this.board = board;
        this.ChessPiece = ChessPiece;
    }

    @Override
    public void handle(ActionEvent event) {
        switch (this.ChessPiece) {
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