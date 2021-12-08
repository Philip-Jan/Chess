package com.chess;

import javafx.application.Application;
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

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ChoosePlayer(stage);
    }

    private void ChoosePlayer(Stage primaryStage) {

        GridPane grid = createGrid();

        Button White = new Button("White");
        White.setOnAction(e -> {

        });
        Button Black = new Button("Black");
        Black.setOnAction(e -> {

        });
        Button Start = createStartButton(primaryStage);
        Button Exit = createExitButton();

        grid.add(White, 0,0);
        grid.add(Black, 1,0);
        grid.add(Start,0,1);
        grid.add(Exit, 1,1);
        setButtonData(White);
        setButtonData(Black);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setCenter(grid);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
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
        Start.setOnAction(e -> startGame(primaryStage));
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
        setButtonData(button);
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