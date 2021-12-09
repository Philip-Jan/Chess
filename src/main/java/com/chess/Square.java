package com.chess;

public class Square {
    Square(){
        this.position=0;
        this.colour=Character.MIN_VALUE;
        this.squarePiece = new Piece(Character.MIN_VALUE);
    }
    int position;
    char colour;
    Piece squarePiece;
    int getRow(){
        return position/8;
    }
    int getColumn(){
        return position%8;
    }
}

