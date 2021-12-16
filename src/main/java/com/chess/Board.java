package com.chess;

import java.util.ArrayList;

public class Board {

    Square[] Squares = new Square[64];
    Board() {
        initBoard();
        setBeginPosition();
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

    public Piece getBoardSquare(int position) {//Returns the piece name that occupies the selected square
        return Squares[position].squarePiece;
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
        if (!piece.moveRecursion) {//short range pieces Knight, King and pawn.
            for (int[] move : piece.movePattern) {
                if (move[0] + Squares[position].getColumn() >= 0 && move[0] + Squares[position].getColumn() <= 7 &&
                        move[1] + Squares[position].getRow() >= 0 && move[1] + Squares[position].getRow() <= 7) { //Is on the board
                    int targetPosition = position + move[0] + move[1] * 8;
                    if (piece.color != Squares[targetPosition].squarePiece.color) { // Is not a piece of the same color
                        validMoves.add(targetPosition);
                    }
                }
            }
        }else {//Long range pieces Queen, Rook and Bishop
            for (int[] move : piece.movePattern) {
                int temp_position = position;
                while (move[0] + Squares[temp_position].getColumn() >= 0 &&
                        move[0] + Squares[temp_position].getColumn() <= 7 &&
                        move[1] + Squares[temp_position].getRow() >= 0 &&
                        move[1] + Squares[temp_position].getRow() <= 7){
                    temp_position = temp_position + move[0] + move[1] * 8;
                    if (piece.color != Squares[temp_position].squarePiece.color) { // Is not a piece of the same color
                        validMoves.add(temp_position);
                    }
                    if (Squares[temp_position].squarePiece.name != Character.MIN_VALUE){ //If there is a piece on the
                        // position, you can't continue in that direction
                        break;
                    }
                }
            }
        }
        return validMoves;
    }

    public ArrayList<ArrayList<Integer>> getAllMoves(char color){// Returns all legal moves a player can make.
        // The first element in each list is the starting position of a piece, the other elements are where it can go.
        ArrayList<ArrayList<Integer>> allValidMoves = new ArrayList<>();
        int pieceNumber = 0;
        for (Square square : Squares){
            if (square.squarePiece.color == color && validMovesPosition(square.position).size() > 0){
                ArrayList<Integer> validMoves = new ArrayList<>();
                validMoves.add(square.position);
                validMoves.addAll(validMovesPosition(square.position));
                allValidMoves.add(new ArrayList<>());
                allValidMoves.get(pieceNumber).addAll(validMoves);
                pieceNumber++;
            }
        }
        return allValidMoves;
    }

    public boolean isCheck(char checker){ //Returns whether the checker is giving a check to the other player.
        Piece enemyKing = new Piece(' ');
        int targetSquare = -1;
        boolean check = false;
        if (checker == 'W') {
            enemyKing.name = 'k';
        }else {
            enemyKing.name = 'K';
        }
        for (Square square : Squares){
            if (square.squarePiece.name == enemyKing.name){
                targetSquare = square.position;
                break;
            }
        }
        for (ArrayList<Integer> move : getAllMoves(checker)){
            if (move.contains(targetSquare)){
                check = true;
                break;
            }
        }
        return check;
    }

    public boolean isMate(char mater){//Returns if the mater is mating the opponent.
        if (!isCheck(mater)){//No check means no mate
            return false;
        }
        char opponent;
        if (mater == 'B'){
            opponent = 'W';
        }else {
            opponent = 'B';
        }
        for (ArrayList<Integer> move : getAllMoves(opponent)){
            for (int i = 1; i < move.size();i++){
                if (!(Squares[move.get(i)].squarePiece.name == 'K' ||
                        Squares[move.get(i)].squarePiece.name == 'k')) { //not a king
                    Piece tempPiece = Squares[move.get(i)].squarePiece; //temporarily moves a piece from the end move position to safety
                    makeMove(move.get(0), move.get(i)); //moves the piece
                    if(!isCheck(mater)){
                        makeMove(move.get(i),move.get(0));
                        Squares[move.get(i)].squarePiece = tempPiece; //Undoes the moves
                        return false;
                    }
                    makeMove(move.get(i),move.get(0));
                   Squares[move.get(i)].squarePiece = tempPiece; //Undoes the moves
                }
            }
        }
        return true;
    }
}
