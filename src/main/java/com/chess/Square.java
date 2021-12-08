package com.chess;

public class Square {
    Square(){
        this.position=0;
        this.colour="";
        this.squarePiece = new Piece("");
    }
    int position;
    String colour;
    Piece squarePiece;
}
