package com.chess;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game {

    Game(Stage stage, boolean isWhite, int fieldColor) {
        this.isWhite = isWhite;
        this.fieldColor = fieldColor;
        startGame(stage);
    }

    private final boolean isWhite;
    private final int fieldColor;
    private ArrayList<Integer> validMoves;
    Board board = new Board();
    private int BoardSquare;
    private Piece ChessPiece;

    public void startGame(Stage stage) {

        GridPane grid = createGrid();

        if (isWhite) {

            for (int i = 0; i < 64; i++) {
                Button button = createNumberButton(i);
                int row = (63-i) / 8;
                int col = i % 8;
                button.setGraphic(board.getBoardSquare(i).imgViewPiece);
                grid.add(button, col, row);
            }
        }
        else {

            for (int i = 0; i < 64; i++) {
                Button button = createNumberButton(i);
                int row = i / 8;
                int col = (63-i) % 8;
                button.setGraphic(board.getBoardSquare(i).imgViewPiece);
                grid.add(button, col, row);
            }
        }

        ButtonBar bar = new ButtonBar();

        Button NewGame1 = new Button("New Game");
        NewGame1.setTooltip(new Tooltip("Behoudt niet de huidige instellingen"));
        NewGame1.setOnAction(e -> {

        });
        NewGame1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setButtonData(NewGame1);
        Button NewGame2 = new Button("New Game");
        NewGame2.setTooltip(new Tooltip("Behoudt de huidige instellingen"));
        NewGame2.setOnAction(e -> {

        });
        NewGame2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setButtonData(NewGame2);
        Button Exit = createExitButton();
        bar.getButtons().addAll(NewGame1, NewGame2, Exit);
        ButtonBar.setButtonData(NewGame1, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(NewGame2, ButtonBar.ButtonData.LEFT);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(bar);
        root.setCenter(grid);

        Scene scene = new Scene(root, 800, 800);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Button createExitButton() {
        Button Exit = new Button("Exit");
        Exit.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Exit.setOnAction(e -> Platform.exit());
        setButtonData(Exit);
        return Exit;
    }

    private Button createNumberButton(int number) {
        Button button = createButton();
        buttonAction(button, number);
        if (validMoves.contains(number)) {
            button.setStyle("Yellow");
        }
        else if ((number + number/8) % 2 == 1) {
            button.setStyle("-fx-background-color: #ffe9c5");
        }
        else {
            button.setStyle("-fx-background-color: #d08c47");
        }
        return button;
    }

    private Button createButton() {
        Button button = new Button();
        setButtonData(button);
        return button;
    }

    private void setButtonData(Button button) {
        button.setMinSize(50,50);
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10));
        return grid;
    }

    private void buttonAction(Button button, int number) {
        if (this.ChessPiece != null) {
            button.setOnAction(new ButtonHandler(number, this.BoardSquare, this.ChessPiece, board));
            button.setGraphic(this.ChessPiece.imgViewPiece);
            this.BoardSquare = 0;
            this.ChessPiece = null;
            this.validMoves = null;
        }
        else {
            button.setOnAction(e -> {
                this.BoardSquare = board.getPosition(number);
                this.ChessPiece = board.getBoardSquare(number);
                this.validMoves = board.validMovesPosition(number);
            });

        }
    }

}
