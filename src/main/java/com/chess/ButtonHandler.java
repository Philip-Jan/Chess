package com.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonHandler implements EventHandler<ActionEvent> {
    private final int number;
    private final Board board;
    private final Piece ChessPiece;
    private final int BoardSquare;

    ButtonHandler(int number, int BoardSquare, Piece ChessPiece, Board board) {
        this.number = number;
        this.board = board;
        this.ChessPiece = ChessPiece;
        this.BoardSquare = BoardSquare;
    }

    @Override
    public void handle(ActionEvent event) {
        if (board.validMovesPosition(BoardSquare).contains(number)) {
            board.makeMove(BoardSquare, number);
        }
        else {

        }
    }

}