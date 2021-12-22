package com.chess;

import javafx.application.Platform;
import javafx.geometry.HPos;
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

import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class Game {

    private final boolean isWhite;
    private final int fieldColor;
    private final int pieceImg;
    private final Button[] buttons = new Button[64];

    Game(Stage stage, boolean isWhite, int fieldColor, int pieceImg) {
        this.isWhite = isWhite;
        this.fieldColor = fieldColor;
        this.pieceImg = pieceImg;
        startGame(stage);
    }

    private ArrayList<Integer> legalMoves;
    private int BoardSquare;
    Board board;
    private Piece ChessPiece;
    private char activePlayer = 'W';

    public void startGame(Stage stage) {

        board = new Board(this.activePlayer, this.pieceImg); // Generate Board

        GridPane grid = createGrid();

        if (!isWhite) {
            for (int i = 0; i < 64; i++) {
                buttons[i] = createNumberButton(i);
                int row = i / 8;
                int col = (63-i) % 8;
                if (board.getBoardSquare(i) != null) {
                    buttons[i].setGraphic(board.getBoardSquare(i).imgViewPiece);
                }
                grid.add(buttons[i], col + 1, row + 1);
                addLabels(grid, 0);
                addLabels(grid, 9);
            }
        } // Generate pieces on the board if player 1 plays with the black pieces
        else {
            for (int i = 0; i < 64; i++) {
                buttons[i] = createNumberButton(i);
                int row = (63-i) / 8;
                int col = i % 8;
                if (board.getBoardSquare(i) != null) {
                    buttons[i].setGraphic(board.getBoardSquare(i).imgViewPiece);
                }
                grid.add(buttons[i], col + 1, row + 1);
                addLabels(grid, 0);
                addLabels(grid, 9);
            }
        } // Generate pieces on the board if player 1 plays with the white pieces or default settings.

        for (int i=0; i < 64; i++) {
            final int number = i;
            buttons[i].setOnAction(e -> {
                if (this.ChessPiece != null) {
                    if (board.legalMovesPosition(BoardSquare).contains(number)) {
                        board.makeMove(BoardSquare, number);
                        buttons[BoardSquare].setGraphic(null);
                        buttons[number].setGraphic(this.ChessPiece.imgViewPiece);

                        if (!board.isMate(activePlayer) && board.isCheck(activePlayer)) {
                            Alert alert = new Alert(INFORMATION, "Check");
                            alert.show();
                        }

                        if (board.isMate(activePlayer)) {
                            Mate(activePlayer);
                            stage.close();
                        }

                        if (activePlayer == 'W'){ //switches active player
                            activePlayer = 'B';
                        }
                        else {
                            activePlayer = 'W';
                        }
                    }
                    this.BoardSquare = 0;
                    this.ChessPiece = null;
                    this.legalMoves = null;
                    for (int k = 0; k < 64; k++) {
                        setStyle(buttons[k], k);
                    }
                }
                else if (board.getBoardSquare(number) != null && board.getBoardSquare(number).color == activePlayer) {
                        this.BoardSquare = board.getPosition(number);
                        this.ChessPiece = board.getBoardSquare(number);
                        this.legalMoves = board.legalMovesPosition(number);
                        for (int k = 0; k < 64; k++) {
                            if (legalMoves.contains(k)) {
                                buttons[k].setStyle("-fx-background-color: Yellow");
                            } else {
                                setStyle(buttons[k], k);
                            }
                        }
                    }
            });
        }

        ButtonBar bar = new ButtonBar();

        Button NewGame = new Button("New Game");
        NewGame.setOnAction(e -> {
            NewGameAction();
            stage.close();
        });
        NewGame.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setButtonData(NewGame);

        Button OfferDraw = new Button("Offer Draw");
        OfferDraw.setOnAction(e -> {
            Alert alert = new Alert(INFORMATION, "The game has ended in a draw.");
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
            Alert alert = new Alert(INFORMATION, "You have lost this game.");
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

        Scene scene = new Scene(root, 900, 800);
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

    private void NewGameAction() {
            Alert alert = new Alert(INFORMATION, "Do you wish to keep the current settings?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == ButtonType.YES) {
                    Stage pStage = new Stage();
                    new Game(pStage, isWhite, fieldColor, pieceImg);
                }
                else if (result.get() == ButtonType.NO) {
                    Stage pStage = new Stage();
                    new Menu(pStage);
                }
            }
    }

    private Button createNumberButton(int number) {
        Button button = createButton();
        setStyle(button, number);
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

    private void setStyle(Button button, int number) {
        if ((number + number/8) % 2 == 1) {
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
    }

    private void Mate(char player) {
        String colors = "";
        if (player == 'W') {
            colors = "White";
        }
        else if (player == 'B') {
            colors = "Black";
        }
            Alert alert = new Alert(INFORMATION, colors + " wins. \n Do you wish to play a new game?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isEmpty()) {
                Platform.exit();
            }
            else if (result.get() == ButtonType.YES) {
                NewGameAction();
            }
            else if (result.get() == ButtonType.NO) {
                Platform.exit();
            }
    }

    private void addLabels(GridPane grid, int pos) {

        int colorW = 1;
        int beginPosW = 0;
        int colorB = 1;
        int beginPosB = 0;

        if (isWhite) {
            colorW = -1;
            beginPosW = 9;
        }
        else {
            colorB = -1;
            beginPosB = 9;
        }

        Label labelA = new Label("A");
        Label labelB = new Label("B");
        Label labelC = new Label("C");
        Label labelD = new Label("D");
        Label labelE = new Label("E");
        Label labelF = new Label("F");
        Label labelG = new Label("G");
        Label labelH = new Label("H");
        Label label1 = new Label("1");
        Label label2 = new Label("2");
        Label label3 = new Label("3");
        Label label4 = new Label("4");
        Label label5 = new Label("5");
        Label label6 = new Label("6");
        Label label7 = new Label("7");
        Label label8 = new Label("8");

        setGridLabel(labelA);
        setGridLabel(labelB);
        setGridLabel(labelC);
        setGridLabel(labelD);
        setGridLabel(labelE);
        setGridLabel(labelF);
        setGridLabel(labelG);
        setGridLabel(labelH);
        setGridLabel(label1);
        setGridLabel(label2);
        setGridLabel(label3);
        setGridLabel(label4);
        setGridLabel(label5);
        setGridLabel(label6);
        setGridLabel(label7);
        setGridLabel(label8);

        grid.add(label1, pos, beginPosW + colorW);
        grid.add(label2, pos, beginPosW + 2 * colorW);
        grid.add(label3, pos, beginPosW + 3 * colorW);
        grid.add(label4, pos, beginPosW + 4 * colorW);
        grid.add(label5, pos, beginPosW + 5 * colorW);
        grid.add(label6, pos, beginPosW + 6 * colorW);
        grid.add(label7, pos, beginPosW + 7 * colorW);
        grid.add(label8, pos, beginPosW + 8 * colorW);
        grid.add(labelA, beginPosB + colorB,pos);
        grid.add(labelB, beginPosB + 2 * colorB,pos);
        grid.add(labelC, beginPosB + 3 * colorB,pos);
        grid.add(labelD, beginPosB + 4 * colorB,pos);
        grid.add(labelE, beginPosB + 5 * colorB,pos);
        grid.add(labelF, beginPosB + 6 * colorB,pos);
        grid.add(labelG, beginPosB + 7 * colorB,pos);
        grid.add(labelH, beginPosB + 8 * colorB,pos);
    }

    private void setGridLabel(Label label) {
        label.setPrefSize(9, 9);
        GridPane.setFillWidth(label, true);
        GridPane.setFillHeight(label, true);
        GridPane.setHalignment(label, HPos.CENTER);
    }
}
