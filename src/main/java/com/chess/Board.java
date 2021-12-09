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
        //White pieces
        Squares[0].squarePiece = new Piece("WRook");
        Squares[1].squarePiece = new Piece("WKnight");
        Squares[2].squarePiece = new Piece("WBishop");
        Squares[3].squarePiece = new Piece("WQueen");
        Squares[4].squarePiece = new Piece("WKing");
        Squares[5].squarePiece = new Piece("WBishop");
        Squares[6].squarePiece = new Piece("WKnight");
        Squares[7].squarePiece = new Piece("WRook");
        for (int i =8; i < 16; i++){
            Squares[i].squarePiece = new Piece("WPawn");
        }
        //Black pieces
        for (int i =48; i < 56; i++){
            Squares[i].squarePiece = new Piece("BPawn");
        }
        Squares[56].squarePiece = new Piece("BRook");
        Squares[57].squarePiece = new Piece("BKnight");
        Squares[58].squarePiece = new Piece("BBishop");
        Squares[59].squarePiece = new Piece("BQueen");
        Squares[60].squarePiece = new Piece("BKing");
        Squares[61].squarePiece = new Piece("BBishop");
        Squares[62].squarePiece = new Piece("BKnight");
        Squares[63].squarePiece = new Piece("BRook");
    }

    public String getBoardSquare(int position) {
        return Squares[position].squarePiece.name;
    }
    public void printFullBoard(){
        for (int i = 0; i < 64; i++)
            if (!Squares[i].squarePiece.name.equals("")) {
                System.out.println(i + " " + Squares[i].squarePiece.name);
            }
    }
    public void printSquareBoard(){
        for (int i = 0; i<8;i++){
            for (int j = 0; j<8;j++){
                if (Squares[(7-i)*8+j].squarePiece.name.equals("")) {
                    System.out.print("~\t");
                }else {
                    System.out.print(Squares[(7-i)*8+j].squarePiece.name + "\t");
                }
            }
            System.out.println("");
        }
    }
    public void makeMove(int startSquare,int endSquare){

    }
}