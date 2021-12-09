package com.chess;

import java.util.ArrayList;

public class Board {

    Square[] Squares = new Square[64];
    Board() {
        initBoard();
        //setBeginPosition();
    }
    

    private void initBoard(){//Makes the board out of an array of squares
        for (int i = 0; i<64; i++){
            Squares[i] = new Square();
            Squares[i].position = i;
            if ((i/8 + i)%2 == 1) {
                Squares[i].colour = 'W';
            }else {
                Squares[i].colour = 'B';
        }

            
        }
    }

    public void setBeginPosition() {//Sets the board to the starting position
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

    public char getBoardSquare(int position) {//Returns the piece name that occupies the selected square
        return Squares[position].squarePiece.name;
    }
    public void printFullBoard(){//Prints the location and piece name of every non-empty square on the board
        for (int i = 0; i < 64; i++)
            if (Squares[i].squarePiece.name != Character.MIN_VALUE) {
                System.out.println(Squares[i].position + " " + Squares[i].squarePiece.name);
            }
    }
    public void printSquareBoard(){//Prints the entire board in an 8 by 8 square
        for (int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (Squares[(7 - i) * 8 + j].squarePiece.name == Character.MIN_VALUE) {
                    System.out.print("~\t");
                } else {
                    System.out.print(Squares[(7 - i) * 8 + j].squarePiece.name + "\t");
                }
            }
            System.out.print("\n");
        }
    }
    public void makeMove(int startSquare,int endSquare){//Moves a piece from the starting location to the end location.
        // If there was a piece on the end location, it is removed.
        Squares[endSquare].squarePiece = Squares[startSquare].squarePiece;
        Squares[startSquare].squarePiece = new Piece(Character.MIN_VALUE);
    }
    public ArrayList<Integer> validMovesPosition(int position){//Give the legal moves the piece on the specified position can make.
        ArrayList<Integer> validMoves = new ArrayList<>();
        Piece piece = Squares[position].squarePiece;
        for (int[] move : piece.movePattern
             ) {
            if (move[0]+ Squares[position].getColumn() >= 0 && move[0]+ Squares[position].getColumn() <= 7 &&
                    move[1]+ Squares[position].getRow() >= 0 && move[1]+ Squares[position].getRow() <= 7) { //Is on the board
                int targetPosition = position+move[0]+move[1]*8;
                if (piece.color != Squares[targetPosition].squarePiece.color){ // Is not a piece of the same color
                    validMoves.add(targetPosition);
                }
            }
        }
        return validMoves;
    }
}