package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece{
    Knight() {
        Moves();
    }

    char name = super.name;
    char color = super.color;
    int[][] movePattern = super.movePattern;//{Change in columns, change in rows}
    boolean moveRecursion = super.moveRecursion;
    Image imgPiece = super.imgPiece;
    ImageView imgViewPiece = super.imgViewPiece;

    @Override
    protected void Moves() {
        movePattern = new int[][] {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        moveRecursion = false;
    }
}
