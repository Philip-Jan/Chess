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
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class Game {

    private final boolean isWhite;
    private final int fieldColor;
    private final int pieceImg;
    private int CurrentTurn;
    private int PreviousTurnW;
    private int PreviousTurnB;
    private final ArrayList<StringBuilder> RepetitionCheckW = new ArrayList<>();
    private final ArrayList<StringBuilder> RepetitionCheckB = new ArrayList<>();
    private char pieceName;
    private ChoiceDialog<Character> PromotionChoicesW;
    private ChoiceDialog<Character> PromotionChoicesB;
    private final Button[] buttons = new Button[64];
    private final Stage stage;
    private ArrayList<Integer> legalMoves;
    private int BoardSquare;
    Board board;
    private Piece ChessPiece;
    private char activePlayer = 'W';

    Game(Stage stage, boolean isWhite, int fieldColor, int pieceImg) {
        this.isWhite = isWhite;
        this.fieldColor = fieldColor;
        this.pieceImg = pieceImg;
        this.stage = stage;
        startGame(stage);
    }

    public void startGame(Stage stage) {

        PromotionChoicesW = new ChoiceDialog<>(' ', 'R', 'B', 'N', 'Q');
        PromotionChoicesB = new ChoiceDialog<>(' ', 'r', 'b', 'n', 'q');

        board = new Board(this.activePlayer, this.pieceImg); // Generate Board
        RepetitionCheckW.add(board.BoardState());
        RepetitionCheckB.add(board.BoardState());

        GridPane grid = createGrid();

        if (!isWhite) {
            for (int i = 0; i < 64; i++) {
                buttons[i] = createNumberButton(i);
                int row = i / 8;
                int col = (63-i) % 8;
                if (board.getPiece(i) != null) {
                    buttons[i].setGraphic(board.getPiece(i).imgViewPiece);
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
                if (board.getPiece(i) != null) {
                    buttons[i].setGraphic(board.getPiece(i).imgViewPiece);
                }
                grid.add(buttons[i], col + 1, row + 1);
                addLabels(grid, 0);
                addLabels(grid, 9);
            }
        } // Generate pieces on the board if player 1 plays with the white pieces or default settings.

        if(board.getAllLegalMoves(activePlayer).isEmpty()) {
            GameDrawn();
        } // If a player has no moves to make but it's his turn
        // then the game draws.

        for (int i=0; i < 64; i++) {
            final int number = i;

            for (StringBuilder stateOfBoard : RepetitionCheckW) {
                if (Collections.frequency(RepetitionCheckW, stateOfBoard) == 5) {
                    GameDrawn();
                }
                else if (Collections.frequency(RepetitionCheckW, stateOfBoard) >= 3) {
                    Alert alert = new Alert(INFORMATION, "Do you wish to claim a draw?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isEmpty()) {
                        break;
                    }
                    else if (result.get() == ButtonType.YES) {
                        GameDrawn();
                    }
                }
            } // Check if same position on the board is reached
            // after white's turn, after 3 or 4 times alert pops up asking if the player wants to claim a draw,
            // by 5 times it forces a draw.

            for (StringBuilder stateOfBoard : RepetitionCheckB) {
                if (Collections.frequency(RepetitionCheckB, stateOfBoard) == 5) {
                    GameDrawn();
                }
                else if (Collections.frequency(RepetitionCheckB, stateOfBoard) >= 3) {
                    Alert alert = new Alert(INFORMATION, "Do you wish to claim a draw?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isEmpty()) {
                        break;
                    }
                    else if (result.get() == ButtonType.YES) {
                        GameDrawn();
                    }
                }
            } // Same but after black's turn.

            buttons[i].setOnAction(e -> {

                // If a chess piece has been selected

                if (this.ChessPiece != null &&
                   (board.getPiece(number) == null || board.getPiece(number).color != activePlayer)) {

                    if (this.ChessPiece.name == 'P') {
                        PreviousTurnW = CurrentTurn;
                    }

                    if (this.ChessPiece.name == 'p') {
                        PreviousTurnB = CurrentTurn;
                    }

                    if (board.legalMovesPosition(BoardSquare).contains(number)) {

                        // Promotion

                        if (this.ChessPiece.name == 'p' && BoardSquare / 8 == 1) {
                            PromotionChoicesB.setContentText("Promote Your Pawn");
                            PromotionChoicesB.showAndWait();
                            pieceName = PromotionChoicesB.getSelectedItem();
                        }
                        else if ((this.ChessPiece.name == 'P' && BoardSquare / 8 == 6) ) {
                            PromotionChoicesW.setContentText("Promote Your Pawn");
                            PromotionChoicesW.showAndWait();
                            pieceName = PromotionChoicesW.getSelectedItem();
                        }

                        // Make the selected move

                        board.makeMove(BoardSquare, number, pieceName);
                        buttons[BoardSquare].setGraphic(null);

                        // Ensuring images are properly displayed after Castling.

                        if (this.ChessPiece.name == 'K' && BoardSquare == 4 && number == 6) {
                            buttons[5].setGraphic(board.getPiece(5).imgViewPiece);
                        }
                        else if (this.ChessPiece.name == 'K' && BoardSquare == 4 && number == 2) {
                            buttons[3].setGraphic(board.getPiece(3).imgViewPiece);
                        }
                        else if (this.ChessPiece.name == 'k' && BoardSquare == 60 && number == 62) {
                            buttons[61].setGraphic(board.getPiece(61).imgViewPiece);
                        }
                        else if (this.ChessPiece.name == 'k' && BoardSquare == 60 && number == 58) {
                            buttons[59].setGraphic(board.getPiece(59).imgViewPiece);
                        }

                        // Ensuring images are properly displayed after En-Passant

                        if ((this.ChessPiece.name == 'P' && BoardSquare / 8 == 4 && number == BoardSquare + 7)
                        || (this.ChessPiece.name == 'p' && BoardSquare / 8 == 3 && number == BoardSquare - 9)) {
                            buttons[BoardSquare - 1].setGraphic(null);
                        }
                        else if ((this.ChessPiece.name == 'P' && BoardSquare / 8 == 4 && number == BoardSquare + 9)
                        || (this.ChessPiece.name == 'p' && BoardSquare / 8 == 3 && number == BoardSquare - 7) ) {
                            buttons[BoardSquare + 1].setGraphic(null);
                        }

                        // Set image on the new square after a move

                        buttons[number].setGraphic(board.getPiece(number).imgViewPiece);

                        // Check if a player is in check

                        if (!board.isMate(activePlayer) && board.isCheck(activePlayer)) {
                            Alert alert = new Alert(INFORMATION, "Check");
                            alert.show();
                        }

                        // Check for mate

                        if (board.isMate(activePlayer)) {
                            Mate(activePlayer);
                            stage.close();
                        }

                        // Check for 50 move rule

                        if(board.movesSinceLastCaptureOrPawn==100){
                            GameDrawn();
                            stage.close();
                        }

                        //switches active player

                        if (activePlayer == 'W'){
                            activePlayer = 'B';
                        }
                        else {
                            activePlayer = 'W';
                        }
                    }

                    // Saving the current state of the board.

                    if (activePlayer == 'W') {
                        int KingPosition = 0;
                        for (int k = 0; k < 64; k++) {
                            if (board.getPiece(k) != null) {
                                if (board.getPiece(k).name == 'K') {
                                    KingPosition = k;
                                }
                            }
                        }
                        int amountOfCastleMoves = 0;
                        for (Integer ignored : board.castleOptions(board.getPiece(KingPosition))) {
                            amountOfCastleMoves ++;
                        }
                        StringBuilder stateOfBoard = board.BoardState();
                        stateOfBoard.append(Arrays.toString(board.EnPassantAllowedW));
                        stateOfBoard.append(amountOfCastleMoves);
                        RepetitionCheckW.add(stateOfBoard);
                    }
                    else if (activePlayer == 'B') {
                        int KingPosition = 0;
                        for (int k = 0; k < 64; k++) {
                            if (board.getPiece(k) != null) {
                                if (board.getPiece(k).name == 'k') {
                                    KingPosition = k;
                                }
                            }
                        }
                        int amountOfCastleMoves = 0;
                        for (Integer ignored : board.castleOptions(board.getPiece(KingPosition))) {
                            amountOfCastleMoves ++;
                        }
                        StringBuilder stateOfBoard = board.BoardState();
                        stateOfBoard.append(Arrays.toString(board.EnPassantAllowedB));
                        stateOfBoard.append(amountOfCastleMoves);
                        RepetitionCheckB.add(stateOfBoard);
                    }

                    // Ensuring En-Passant works only for the one turn
                    // after the other player moves one of their pawns two spaces from its starting position.

                    if (CurrentTurn == PreviousTurnW + 1) {
                        for (int j = 0; j < 8; j++) {
                            board.EnPassantAllowedW[j] = false;
                        }
                    }
                    if (CurrentTurn == PreviousTurnB + 1) {
                        for (int j = 0; j < 8; j++) {
                            board.EnPassantAllowedB[j] = false;
                        }
                    }

                    // Keeping track of the Current turn.

                    CurrentTurn ++;

                    // Resetting variables.

                    this.pieceName = ' ';
                    this.BoardSquare = 0;
                    this.ChessPiece = null;
                    this.legalMoves = null;
                    for (int k = 0; k < 64; k++) {
                        setStyle(buttons[k], k);
                    }
                }

                // If no chess piece has been selected, ensure only the correct set of pieces can be selected.
                // Set variables.

                else if (board.getPiece(number) != null && board.getPiece(number).color == activePlayer) {
                    this.BoardSquare = board.getPosition(number);
                    this.ChessPiece = board.getPiece(number);
                    this.legalMoves = board.legalMovesPosition(number);
                    for (int k = 0; k < 64; k++) {
                        if (legalMoves.contains(k)) {
                            buttons[k].setStyle("-fx-background-color: Yellow");
                        }
                        else {
                            setStyle(buttons[k], k);
                        }
                    }
                }
            });
        }

        // Make button for New Game

        Button NewGame = new Button("New Game");
        NewGame.setOnAction(e -> NewGameAction());
        NewGame.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setMenuButtonData(NewGame);

        // Make button for offering a draw

        Button OfferDraw = new Button("Offer Draw");
        OfferDraw.setOnAction(e -> GameDrawn() );
        OfferDraw.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setMenuButtonData(OfferDraw);

        // Make button for offering a win

        Button Resign = new Button("Resign");
        Resign.setOnAction(e -> {
            Alert alert = new Alert(INFORMATION, "You have lost this game. \n Do you want to play a new game?",
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
        });
        Resign.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setMenuButtonData(Resign);

        // Make button for exit

        Button Exit = createExitButton();
        setMenuButtonData(Exit);

        // Add buttons to button bar

        ButtonBar bar = new ButtonBar();

        bar.getButtons().addAll(NewGame, OfferDraw, Resign, Exit);
        ButtonBar.setButtonData(NewGame, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(OfferDraw, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(Resign, ButtonBar.ButtonData.LEFT);

        // Set everything previously created into a borderpane.

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(bar);
        root.setCenter(grid);

        // Actually show the application.

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
    } // Create Exit button

    private Button createNumberButton(int number) {
        Button button = createButton();
        setStyle(button, number);
        return button;
    } // Ensuring buttons conform to correct color
    // and can be uniquely identified.

    private Button createButton() {
        Button button = new Button();
        setButtonData(button);
        return button;
    } // Creating buttons and setting its settings.

    private void setButtonData(Button button) {
        button.setPrefSize(110,100);
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
    } // The settings for the buttons that make up the chessboard.

    private void setMenuButtonData(Button button) {
        button.setPrefSize(200,30);
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
    } // The settings for the buttons on the button bar.

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
    } // The colors of the chessboard buttons,
    // based on chosen fieldColor in Menu.

    private void NewGameAction() {
        Alert alert = new Alert(INFORMATION, "Do you wish to keep the current settings?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.YES) {
                Stage pStage = new Stage();
                new Game(pStage, isWhite, fieldColor, pieceImg);
                stage.close();
            }
            else if (result.get() == ButtonType.NO) {
                Stage pStage = new Stage();
                new Menu(pStage);
                stage.close();
            }
        }
    } // Alert for starting a new game

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
    } // Alert that pops up if one player checkmates the other player.

    private void GameDrawn() {
        Alert alert = new Alert(INFORMATION, "The game has ended in a draw. \n Do you want to play a new game?",
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
    } // Alert for when the game is a draw.

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10));
        return grid;
    } // The grid.

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
    } // Add labels to the grid,
    // that is the numbers 1-8 on the sides and letters A-H on top and bottom.

    private void setGridLabel(Label label) {
        label.setPrefSize(9, 9);
        GridPane.setFillWidth(label, true);
        GridPane.setFillHeight(label, true);
        GridPane.setHalignment(label, HPos.CENTER);
    } // Set size of labels.
}
