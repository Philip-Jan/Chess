package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece{
    Pawn() {
        Moves();
    }

    char name = super.name;
    char color = super.color;
    int[][] movePattern = super.movePattern;//{Change in columns, change in rows}
    int[] specialMoves = super.specialMoves; //Castling, pawn captures, double pawn move
    boolean moveRecursion = super.moveRecursion;
    Image imgPiece = super.imgPiece;
    ImageView imgViewPiece = super.imgViewPiece;

    @Override
    protected void Moves() {
        movePattern = new int[][]{{0,1}};
        specialMoves = new int[]{16,7,9};
        moveRecursion = false;
    }
}
