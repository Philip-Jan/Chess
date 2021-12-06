package com.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Objects;

public class ButtonHandler implements EventHandler<ActionEvent> {
    private final int number;
    private final Button button;
    private String ChessPiece;
    ButtonHandler(int number, Button button) {
        this.number = number;
        this.button = button;
        this.ChessPiece = "";
    }

    @Override
    public void handle(ActionEvent event) {
        if (Objects.equals(ChessPiece, "")) {

        }
        else if (Objects.equals(ChessPiece, "Witte Koning")) {

        }
        else if (Objects.equals(ChessPiece, "Witte Koningin")) {

        }
        else if (Objects.equals(ChessPiece, "Witte Loper")) {

        }
        else if (Objects.equals(ChessPiece, "Wit Paard")) {

        }
        else if (Objects.equals(ChessPiece, "Witte Toren")) {

        }
        else if (Objects.equals(ChessPiece, "Witte Pion")) {

        }
        else if (Objects.equals(ChessPiece, "Zwarte Koning")) {

        }
        else if (Objects.equals(ChessPiece, "Zwarte Koningin")) {

        }
        else if (Objects.equals(ChessPiece, "Zwarte Loper")) {

        }
        else if (Objects.equals(ChessPiece, "Zwart Paard")) {

        }
        else if (Objects.equals(ChessPiece, "Zwarte Toren")) {

        }
        else if (Objects.equals(ChessPiece, "Zwarte Pion")) {

        }
    }

}