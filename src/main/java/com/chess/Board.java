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
        Squares[0].squarePiece = new Piece('R');
        Squares[1].squarePiece = new Piece('N');
        Squares[2].squarePiece = new Piece('B');
        Squares[3].squarePiece = new Piece('Q');
        Squares[4].squarePiece = new Piece('K');
        Squares[5].squarePiece = new Piece('B');
        Squares[6].squarePiece = new Piece('N');
        Squares[7].squarePiece = new Piece('R');
        for (int i =8; i < 16; i++){
            Squares[i].squarePiece = new Piece('P');
        }
        //Black pieces
        for (int i =48; i < 56; i++){
            Squares[i].squarePiece = new Piece('p');
        }
        Squares[56].squarePiece = new Piece('r');
        Squares[57].squarePiece = new Piece('n');
        Squares[58].squarePiece = new Piece('b');
        Squares[59].squarePiece = new Piece('q');
        Squares[60].squarePiece = new Piece('k');
        Squares[61].squarePiece = new Piece('b');
        Squares[62].squarePiece = new Piece('n');
        Squares[63].squarePiece = new Piece('r');
    }

    public char getBoardSquare(int position) {
        return Squares[position].squarePiece.name;
    }
    public void printFullBoard(){
        for (int i = 0; i < 64; i++)
            if (Squares[i].squarePiece.name != Character.MIN_VALUE) {
                System.out.println(Squares[i].position + " " + Squares[i].squarePiece.name);
            }
    }
    public void printSquareBoard(){
        for (int i = 0; i<8;i++){
            for (int j = 0; j<8;j++){
                if (Squares[(7-i)*8+j].squarePiece.name == Character.MIN_VALUE) {
                    System.out.print("~\t");
                }else {
                    System.out.print(Squares[(7-i)*8+j].squarePiece.name + "\t");
                }
            }
            System.out.print("\n");
        }
    }
    public void makeMove(int startSquare,int endSquare){
        Squares[endSquare].squarePiece = Squares[startSquare].squarePiece;
        Squares[startSquare].squarePiece = new Piece(Character.MIN_VALUE);
    }
}