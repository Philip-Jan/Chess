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
        }else {
            launch(args);
        }
    }
}
