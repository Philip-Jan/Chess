package com.chess;

public class Board {

    Square[] Squares = new Square[64];
    Board() {
        initBoard();
        //setBeginPosition();
    }
    

    private void initBoard(){
        for (int i = 0; i<64; i++){
            Squares[i] = new Square();
            Squares[i].position = i;
            if ((i/8 + i)%2 == 1) {
                Squares[i].colour = "W";
            }else {
                Squares[i].colour = "B";
        }

            
        }
    }

    public void setBeginPosition() {
        Squares[0].squarePiece = new Piece("WRook");
    }

    public String getBoardSquare(int position) {
        return Squares[position].squarePiece.name;
    }
    public void makeMove(int startSquare,int endSquare){

    }
}