package com.chess;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) {
        new Menu(stage);
    }

    public static void main(String[] args) {
        boolean debugMode = false;
        if (debugMode){
            Board board = new Board();
            board.setBeginPosition();
            board.printSquareBoard();
            System.out.println(board.activePlayer);
            System.out.println(board.getAllMoves('B'));
            System.out.println("White checks: " + board.isCheck('W'));
            System.out.println("Black checks:" +  board.isCheck('B'));
            System.out.println("White mates: " + board.isMate('W'));
            System.out.println("Black mates:" +  board.isMate('B'));
            board.makeMove(3,53);
            System.out.println(board.activePlayer);
            board.printSquareBoard();
            System.out.println("White checks: " + board.isCheck('W'));
            System.out.println("Black checks:" +  board.isCheck('B'));
            System.out.println("White mates: " + board.isMate('W'));
            System.out.println("Black mates:" +  board.isMate('B'));
            board.makeMove(5,44);
            board.printSquareBoard();
            System.out.println("White checks: " + board.isCheck('W'));
            System.out.println("Black checks:" +  board.isCheck('B'));
            System.out.println("White mates: " + board.isMate('W'));
            System.out.println("Black mates:" +  board.isMate('B'));
            System.out.println("Black king valid moves:" + board.validMovesPosition(60));
            System.out.println("Black king legal moves:" + board.legalMovesPosition(60));
            System.out.println("Black b-knight valid moves:" + board.validMovesPosition(57));
            System.out.println("Black b-knight legal moves:" + board.legalMovesPosition(57));
            board.makeMove(60,36);
            board.printSquareBoard();
            System.out.println("Black king valid moves:" + board.validMovesPosition(36));
            System.out.println("Black king legal moves:" + board.legalMovesPosition(36));
        }else {
            launch(args);
        }
    }
}
