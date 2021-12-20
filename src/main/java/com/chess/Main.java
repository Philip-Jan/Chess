package com.chess;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) {
        new Menu(stage);
    }

    public static void main(String[] args) {
        boolean debugMode = true;
        if (debugMode){
            Board board = new Board();
            board.setBeginPosition();
            board.printSquareBoard();
            System.out.println(board.activePlayer);
            System.out.println(board.getAllMoves('B'));
            System.out.println(board.isCheck('W'));
            System.out.println(board.isCheck('B'));
            board.makeMove(3,51);
            System.out.println(board.activePlayer);
            board.printSquareBoard();
            System.out.println(board.isCheck('W'));
            System.out.println(board.isCheck('B'));
            board.makeMove(60,5);
            board.printSquareBoard();
            System.out.println(board.isCheck('W'));
            System.out.println(board.isCheck('B'));
        }else {
            launch(args);
        }
    }
}
