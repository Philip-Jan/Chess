package com.chess;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Button Options = new Button();
        Button Difficulty = new Button();

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
        setButtonData(Options);
        Options.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
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
        Start.setOnAction(e -> new Game(primaryStage));
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