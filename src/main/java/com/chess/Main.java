package com.chess;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ChoosePlayer(stage);
    }

    private boolean isWhite;
    private int fieldColor;
    private ChoiceBox<String> Options;

    private void ChoosePlayer(Stage primaryStage) {

        GridPane grid = createGrid();

        Button White = new Button("White");
        White.setOnAction(e -> isWhite = true);
        Button Black = new Button("Black");
        Black.setOnAction(e -> isWhite = false);
        Button Start = createStartButton(primaryStage);
        Button Exit = createExitButton();
        this.Options = OptionsButton();
        Button Difficulty = DifficultyButton();

        grid.add(White, 0,0);
        grid.add(Black, 1,0);
        grid.add(Options,0,1);
        grid.add(Difficulty,1,1);
        grid.add(Start,0,2);
        grid.add(Exit, 1,2);
        setButtonData(White);
        White.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        setButtonData(Black);
        Black.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        setButtonData(Difficulty);
        Difficulty.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setCenter(grid);
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void setButtonData(Button button) {
        button.setMinSize(50,50);
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
    }

    private Button createStartButton(Stage primaryStage) {
        Button Start = new Button("Start");
        Start.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Start.setOnAction(e -> {
            this.fieldColor = setFieldColor();
            new Game(primaryStage, isWhite, fieldColor);
        });
        setButtonData(Start);
        return Start;
    }

    private Button createExitButton() {
        Button Exit = new Button("Exit");
        Exit.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Exit.setOnAction(e -> Platform.exit());
        setButtonData(Exit);
        return Exit;
    }

    private ChoiceBox<String> OptionsButton() {
        ChoiceBox<String> Opt = new ChoiceBox<>();
        Opt.setValue("Default");
        Opt.getItems().add("Default");
        Opt.getItems().add("Green");
        Opt.getItems().add("Purple");
        Opt.getItems().add("Pink");
        Opt.getItems().add("Turquoise");
        Opt.getItems().add("Blue");
        Opt.getItems().add("Grey");
        Opt.getItems().add("Black");
        return Opt;
    }

    private Button DifficultyButton() {
        Button Dif = new Button();
        return Dif;
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10));
        return grid;
    }

    private int setFieldColor() {
        String value = this.Options.getValue();
        switch (value) {
            case "Default" -> fieldColor = 0;
            case "Green" -> fieldColor = 1;
            case "Purple" -> fieldColor = 2;
            case "Pink" -> fieldColor = 3;
            case "Turquoise" -> fieldColor = 4;
            case "Blue" -> fieldColor = 5;
            case "Grey" -> fieldColor = 6;
            case "Black" -> fieldColor = 7;
        }
        return fieldColor;
    }

    public static void main(String[] args) {
        boolean debugMode = false;
        if (debugMode){
            Board board = new Board();
            board.setBeginPosition();
            board.printSquareBoard();
            System.out.println(board.getAllMoves('B'));
            System.out.println(board.isCheck('W'));
            System.out.println(board.isCheck('B'));
            board.makeMove(3,51);
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