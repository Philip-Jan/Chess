package com.chess;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

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

        Button NewGame = new Button("New Game");
        NewGame.setOnAction(e -> {
            Alert alert = new Alert(CONFIRMATION);

        });
        NewGame.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setButtonData(NewGame);

        Button OfferDraw = new Button("Offer Draw");
        OfferDraw.setOnAction(e -> {
            Alert alert = new Alert(INFORMATION, "A draw");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isEmpty()) {
                Platform.exit();
            }
            else if (result.get() == ButtonType.OK) {
                Platform.exit();
            }
        });
        OfferDraw.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setButtonData(OfferDraw);

        Button OfferWin = new Button("Offer Win");
        OfferWin.setOnAction(e -> {
            Alert alert = new Alert(INFORMATION, "You have lost.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isEmpty()) {
                Platform.exit();
            }
            else if (result.get() == ButtonType.OK) {
                Platform.exit();
            }
        });
        OfferWin.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setButtonData(OfferWin);
        Button Exit = createExitButton();
        bar.getButtons().addAll(NewGame, OfferDraw, OfferWin, Exit);
        ButtonBar.setButtonData(NewGame, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(OfferDraw, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(OfferWin, ButtonBar.ButtonData.LEFT);

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
        if (validMoves != null && validMoves.contains(number)) {
            button.setStyle("Yellow");
        }
        else if ((number + number/8) % 2 == 1) {
            switch (fieldColor) {
                case 0 -> button.setStyle("-fx-background-color: #ffe9c5");
                case 1 -> button.setStyle("-fx-background-color: #6f8f72");
                case 2 -> button.setStyle("-fx-background-color: #6f73d2");
                case 3 -> button.setStyle("-fx-background-color: #706677");
                case 4 -> button.setStyle("-fx-background-color: #70a2a3");
                case 5 -> button.setStyle("-fx-background-color: #03357c");
                case 6 -> button.setStyle("-fx-background-color: #b3b3b3");
                case 7 -> button.setStyle("-fx-background-color: #1e1e1e");
            }
        }
        else {
            switch (fieldColor) {
                case 0 -> button.setStyle("-fx-background-color: #d08c47");
                case 1 -> button.setStyle("-fx-background-color: #adbd8f");
                case 2 -> button.setStyle("-fx-background-color: #9dacff");
                case 3 -> button.setStyle("-fx-background-color: #ccb7ae");
                case 4 -> button.setStyle("-fx-background-color: #b1e4b8");
                case 5 -> button.setStyle("-fx-background-color: #bee4f9");
                case 6 -> button.setStyle("-fx-background-color: #e6e6e6");
                case 7 -> button.setStyle("-fx-background-color: #e9e0db");
            }
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
