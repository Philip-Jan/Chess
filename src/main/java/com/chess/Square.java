package com.chess;

public class Square {
    Square(){
        this.position=0;
        this.colour="";
        this.squarePiece = new Piece(Character.MIN_VALUE);
    }
    int position;
    String colour;
    Piece squarePiece;
    int getRow(){
        return position/8;
    }
    int getColumn(){
        return position%8;
    }
}

