package com.chess;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        startGame(stage);
    }

    private void startGame(Stage stage) {

        GridPane grid = createGrid();

        for (int i=0; i<64; i++) {
            Button button = createNumberButton(i);
            int row = i / 8;
            int col = i % 8;
            grid.add(button, col, row);
        }

        ButtonBar bar = new ButtonBar();

        Button NewGame = new Button("New Game");
        NewGame.setOnAction(e -> {

        });
        Button Exit = new Button("Exit");
        Exit.setOnAction(e -> Platform.exit());
        Button White = new Button("White");
        White.setOnAction(e -> {

        });
        Button Black = new Button("Black");
        Black.setOnAction(e -> {

        });
        bar.getButtons().addAll(White, Black, NewGame, Exit);
        ButtonBar.setButtonData(White, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(Black, ButtonBar.ButtonData.LEFT);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(bar);
        root.setLeft(grid);

        Scene scene = new Scene(root, 800, 800);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Button createNumberButton(int number) {
        Button button = createButton(number);
        button.setOnAction(new ButtonHandler(number, button));
        return button;
    }

    private Button createButton(int number) {
        Button button = new Button();
        if (number % 2 == 0 && (number/8) % 2 == 0) {
            button.setStyle("-fx-background-color: #ffe9c5");
        }
        else if (number % 2 == 0 && (number/8) % 2 == 1) {
            button.setStyle("-fx-background-color: #d08c47");
        }
        else if (number % 2 == 1 && (number/8) % 2 == 0) {
            button.setStyle("-fx-background-color: #d08c47");
        }
        else if (number % 2 == 1 && (number/8) % 2 == 1) {
            button.setStyle("-fx-background-color: #ffe9c5");
        }
        button.setMinSize(80,50);
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        return button;
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10));
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}