package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Piece{
    King() {
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
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
    }

}
