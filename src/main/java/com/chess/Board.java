package com.chess;

import java.util.ArrayList;

public class Board {

    private final int pieceImg;
    int targetSquare = -1;
    Square[] Squares = new Square[64];
    char activePlayer;

    Board() {
        this.pieceImg = 0;
        initBoard();
        setBeginPosition();
    }

    Board(char activePlayer, int pieceImg) {
        this.activePlayer = activePlayer;
        this.pieceImg = pieceImg;
        initBoard();
        setBeginPosition();
    }

    private void initBoard(){//Makes the board out of an array of squares

        for (int i = 0; i<64; i++){
            Squares[i] = new Square(this.pieceImg);
            Squares[i].position = i;
            if ((i/8 + i)%2 == 1) {
                Squares[i].colour = 'W';
            }
            else {
                Squares[i].colour = 'B';
            }
        }
    }

    public void setBeginPosition() {//Sets the board to the starting position
        //White pieces
        Squares[0].squarePiece = new Piece('R', this.pieceImg);
        Squares[1].squarePiece = new Piece('N', this.pieceImg);
        Squares[2].squarePiece = new Piece('B', this.pieceImg);
        Squares[3].squarePiece = new Piece('Q', this.pieceImg);
        Squares[4].squarePiece = new Piece('K', this.pieceImg);
        Squares[5].squarePiece = new Piece('B', this.pieceImg);
        Squares[6].squarePiece = new Piece('N', this.pieceImg);
        Squares[7].squarePiece = new Piece('R', this.pieceImg);
        for (int i =8; i < 16; i++){
            Squares[i].squarePiece = new Piece('P', this.pieceImg);
        }
        //Black pieces
        for (int i =48; i < 56; i++){
            Squares[i].squarePiece = new Piece('p', this.pieceImg);
        }
        Squares[56].squarePiece = new Piece('r', this.pieceImg);
        Squares[57].squarePiece = new Piece('n', this.pieceImg);
        Squares[58].squarePiece = new Piece('b', this.pieceImg);
        Squares[59].squarePiece = new Piece('q', this.pieceImg);
        Squares[60].squarePiece = new Piece('k', this.pieceImg);
        Squares[61].squarePiece = new Piece('b', this.pieceImg);
        Squares[62].squarePiece = new Piece('n', this.pieceImg);
        Squares[63].squarePiece = new Piece('r', this.pieceImg);

        //White begins
        activePlayer = 'W';
    }

    public Piece getBoardSquare(int position) {//Returns the piece that occupies the selected square
        return Squares[position].squarePiece;
    }

    public int getPosition(int position) { return position; }

    public void printFullBoard(){//Prints the location and piece name of every non-empty square on the board
        for (int i = 0; i < 64; i++)
            if (Squares[i].squarePiece.name != Character.MIN_VALUE) {
                System.out.println(Squares[i].position + " " + Squares[i].squarePiece.name);
            }
    }

    public void printSquareBoard(){//Prints the entire board in an 8 by 8 square
        for (int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (Squares[(7 - i) * 8 + j].squarePiece == null) {
                    System.out.print("~\t");
                } else {
                    System.out.print(Squares[(7 - i) * 8 + j].squarePiece.name + "\t");
                }
            }
            System.out.print("\n");
        }
    }

    public void makeMove(int startSquare,int endSquare, char pieceName) {
        //Moves a piece from the starting location to the end location.
        // If there was a piece on the end location, it is removed.
        Squares[endSquare].squarePiece = Squares[startSquare].squarePiece;
        Squares[endSquare].squarePiece.hasMoved = true;
        //Promotion
        if (Squares[endSquare].squarePiece.name == 'P' && Squares[endSquare].getRow() == 7){
            Squares[endSquare].squarePiece = new Piece(pieceName, this.pieceImg);
        }
        if (Squares[endSquare].squarePiece.name == 'p' && Squares[endSquare].getRow() == 0){
            Squares[endSquare].squarePiece = new Piece(pieceName, this.pieceImg);
        }
        //Castling
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 4 && endSquare == 6) {
            //white kingside castling
            makeMove(7,5, 'R');
        }
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 4 && endSquare == 2) {
            //white queenside castling
            makeMove(0,3, 'R');
        }
        if (Squares[startSquare].squarePiece.name == 'k' && startSquare == 60 && endSquare == 62) {
            //black kingside castling
            makeMove(63,61, 'r' );
        }
        if (Squares[startSquare].squarePiece.name == 'k' && startSquare == 60 && endSquare == 58) {
            //black queenside castling
            makeMove(56,59, 'r');
        }
        Squares[startSquare].squarePiece = null;
    }

    public void makeTempMove(int startSquare,int endSquare){//Makes a temporary move, useful for checking whether moves
        // can be made, without promoting pawns or giving up castling rights.
        Squares[endSquare].squarePiece = Squares[startSquare].squarePiece;
        //Castling
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 4 && endSquare == 6) {
            //white kingside castling
            makeTempMove(7,5);
        }
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 6 && endSquare == 4) {
            //undoing white kingside castling
            makeTempMove(5,7);
        }
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 4 && endSquare == 2) {
            //white queenside castling
            makeTempMove(0,3);
        }
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 2 && endSquare == 4) {
            //undoing white queenside castling
            makeTempMove(3,0);
        }
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 60 && endSquare == 62) {
            //black kingside castling
            makeTempMove(63,61);
        }
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 62 && endSquare == 60) {
            //undoing black kingside castling
            makeTempMove(61,63);
        }
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 60 && endSquare == 58) {
            //black queenside castling
            makeTempMove(56,59);
        }
        if (Squares[startSquare].squarePiece.name == 'K' && startSquare == 62 && endSquare == 58) {
            //undoing black queenside castling
            makeTempMove(59,56);
        }
        Squares[startSquare].squarePiece = null;
    }

    public ArrayList<Integer> castleOptions (Piece piece){
        ArrayList<Integer> castleMoves = new ArrayList<>();
        if (!piece.hasMoved) {
            // King must not have moved
            if (piece.name == 'K' && !isCheck('B')) {
                //white, not in check
                if (Squares[7].squarePiece != null) {
                    if (!Squares[7].squarePiece.hasMoved && Squares[5].squarePiece == null && Squares[6].squarePiece == null) {
                        //Rook for kingside castling has not moved and there is nothing between the king and rook
                        makeTempMove(4, 5);
                        if (!isCheck('B')) { //no check on square the King moves over
                            makeTempMove(5, 6);
                            if (!isCheck('B')) {
                                castleMoves.add(6);
                            }
                            makeTempMove(6, 5);
                            // Moves the king back. This in-between move is because if there is a check
                            // on the first square, it would not get in this loop.
                        }
                        makeTempMove(5, 4);
                    }
                }
                if (Squares[0].squarePiece != null) {
                    if (!Squares[0].squarePiece.hasMoved && Squares[1].squarePiece == null &&
                            Squares[2].squarePiece == null && Squares[3].squarePiece == null) {
                        //Rook for queenside castling has not moved and there is nothing between the king and rook
                        makeTempMove(4, 3);
                        if (!isCheck('B')) {
                            //no check on square the King moves over
                            makeTempMove(3, 2);
                            if (!isCheck('B')) {
                                castleMoves.add(2);
                            }
                            makeTempMove(2, 3);
                            // Moves the king back. This in-between move is because if there is a check
                            // on the first square, it would not get in this loop.
                        }
                        makeTempMove(3, 4);
                    }
                }
            } else if (piece.name == 'k' && !isCheck('W')) {//Black, not in check
                if (Squares[63].squarePiece != null) {
                    if (!Squares[63].squarePiece.hasMoved && Squares[61].squarePiece == null && Squares[62].squarePiece == null) {
                        //Rook for kingside castling has not moved and there is nothing between the king and rook
                        makeTempMove(60, 61);
                        if (!isCheck('W')) {
                            //no check on square the King moves over
                            makeTempMove(61, 62);
                            if (!isCheck('W')) {
                                castleMoves.add(62);
                            }
                            makeTempMove(62, 61);
                            // Moves the king back. This in-between move is because if there is a check
                            // on the first square, it would not get in this loop.
                        }
                        makeTempMove(61, 60);
                    }
                }
                if (Squares[56].squarePiece != null) {
                    if (!Squares[56].squarePiece.hasMoved && Squares[57].squarePiece == null &&
                            Squares[58].squarePiece == null && Squares[59].squarePiece == null) {
                        //Rook for queenside castling has not moved and there is nothing between the king and rook
                        makeTempMove(60, 59);
                        if (!isCheck('W')) {
                            //no check on square the King moves over
                            makeTempMove(59, 58);
                            if (!isCheck('W')) {
                                castleMoves.add(58);
                            }
                            makeTempMove(58, 59);
                            // Moves the king back. This in-between move is because if there is a check
                            // on the first square, it would not get in this loop.
                        }
                        makeTempMove(59, 60);
                    }
                }
            }
        }
        return castleMoves;
    }

    public ArrayList<Integer> validMovesPosition(int position) {//Give the valid moves the piece on the specified
        // position can make. This method allows the player to keep/put themselves in check.
        ArrayList<Integer> validMoves = new ArrayList<>();
        Piece piece = Squares[position].squarePiece;
        if (!piece.moveRecursion) {//short range pieces Knight, King and pawn.
            if (piece.name == 'P') {//white pawn
                if (Squares[position + 8].squarePiece == null && Squares[position].getRow() < 7) {
                    //empty square in front of pawn
                    validMoves.add(position + 8);
                    if (Squares[position].getRow() == 1) {//start position
                        if (Squares[position + 16].squarePiece == null) {//empty square 2 in front of pawn
                            validMoves.add(position + 16);
                        }
                    }
                }
                if (Squares[position + 7].squarePiece != null) {
                    if (Squares[position + 7].squarePiece.color == 'B' && Squares[position].getColumn() > 0) {
                        //enemy piece diagonally in front of pawn
                        validMoves.add(position + 7);
                    }
                }
                if (Squares[position + 9].squarePiece != null) {
                    if (Squares[position + 9].squarePiece.color == 'B' && Squares[position].getColumn() < 7) {
                        //enemy piece diagonally in front of pawn
                        validMoves.add(position + 9);
                    }
                }
            }
            else if (piece.name == 'p') {//black pawn
                if (Squares[position - 8].squarePiece == null && Squares[position].getRow() > 0) {
                    //empty square in front of pawn
                    validMoves.add(position - 8);
                    if (Squares[position].getRow() == 6) {//start position
                        if (Squares[position - 16].squarePiece == null) {//empty square 2 in front of pawn
                            validMoves.add(position - 16);
                        }
                    }
                }
                if (Squares[position - 9].squarePiece != null) {
                    if (Squares[position - 9].squarePiece.color == 'W' && Squares[position].getColumn() > 0) {
                        //enemy piece diagonally in front of pawn
                        validMoves.add(position - 9);
                    }
                }
                if (Squares[position - 7].squarePiece != null) {
                    if (Squares[position - 7].squarePiece.color == 'W' && Squares[position].getColumn() < 7) {
                        //enemy piece diagonally in front of pawn
                        validMoves.add(position - 7);
                    }
                }
            }
            else {//other pieces
                for (int[] move : piece.movePattern) {
                    if (move[0] + Squares[position].getColumn() >= 0 && move[0] + Squares[position].getColumn() <= 7 &&
                            move[1] + Squares[position].getRow() >= 0 && move[1] + Squares[position].getRow() <= 7) {//Is on the board
                        int targetPosition = position + move[0] + move[1] * 8;
                        if (Squares[targetPosition].squarePiece != null) {
                            if (piece.color != Squares[targetPosition].squarePiece.color) { // Is not a piece of the same color
                                validMoves.add(targetPosition);
                            }
                        }
                        else {
                            validMoves.add(targetPosition);
                        }
                    }
                }

            }
        }
        else {//Long range pieces Queen, Rook and Bishop
            for (int[] move : piece.movePattern) {
                int temp_position = position;
                while (move[0] + Squares[temp_position].getColumn() >= 0 &&
                        move[0] + Squares[temp_position].getColumn() <= 7 &&
                        move[1] + Squares[temp_position].getRow() >= 0 &&
                        move[1] + Squares[temp_position].getRow() <= 7){
                    temp_position = temp_position + move[0] + move[1] * 8;
                    if (Squares[temp_position].squarePiece == null) {
                        validMoves.add(temp_position);
                    }
                    else if (Squares[temp_position].squarePiece != null) {
                        if (piece.color != Squares[temp_position].squarePiece.color) { // Is not a piece of the same color
                            validMoves.add(temp_position);
                        }
                        if (Squares[temp_position].squarePiece.name != Character.MIN_VALUE) { //If there is a piece on the
                            // position, you can't continue in that direction
                            break;
                        }
                    }
                }
            }
        }
        return validMoves;
    }

    public ArrayList<Integer> legalMovesPosition(int position){//Gives the legal moves of the piece on the selected
        // position. This method only returns moves where the player is not in check at the end.
        char checker;
        if (Squares[position].squarePiece.color == 'W'){
            checker = 'B';
        }else {
            checker = 'W';
        }
        ArrayList<Integer> moves = validMovesPosition(position);
        ArrayList<Integer> legalMoves = new ArrayList<>();
        for (Integer move : moves) {
            if (Squares[move].squarePiece != null) { // if the move would capture a piece
                Piece tempPiece = Squares[move].squarePiece; //temporarily stores the would-be-captured piece
                makeTempMove(position, move);
                if (!isCheck(checker)) {
                    legalMoves.add(move);
                }
                makeTempMove(move, position);
                Squares[move].squarePiece = tempPiece;
            } else {
                makeTempMove(position, move);
                if (!isCheck(checker)) {
                    legalMoves.add(move);
                }
                makeTempMove(move, position);
            }
        }
        Piece piece = Squares[position].squarePiece;
        if (!piece.hasMoved && (piece.name == 'k' || piece.name == 'K')){
            legalMoves.addAll(castleOptions(piece));
        }
        return  legalMoves;
    }

    public ArrayList<ArrayList<Integer>> getAllMoves(char color){// Returns all legal moves a player can make.
        // The first element in each list is the starting position of a piece, the other elements are where it can go.
        ArrayList<ArrayList<Integer>> allValidMoves = new ArrayList<>();
        int pieceNumber = 0;
        for (Square square : Squares){
            if (square.squarePiece != null && square.squarePiece.color == color && validMovesPosition(square.position).size() > 0){
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

    public ArrayList<ArrayList<Integer>> getAllLegalMoves(char color){// Returns all legal moves a player can make.
        // The first element in each list is the starting position of a piece, the other elements are where it can go.
        ArrayList<ArrayList<Integer>> allLegalMoves = new ArrayList<>();
        int pieceNumber = 0;
        for (Square square : Squares){
            if (square.squarePiece != null && square.squarePiece.color == color && legalMovesPosition(square.position).size() > 0){
                ArrayList<Integer> legalMoves = new ArrayList<>();
                legalMoves.add(square.position);
                legalMoves.addAll(validMovesPosition(square.position));
                allLegalMoves.add(new ArrayList<>());
                allLegalMoves.get(pieceNumber).addAll(legalMoves);
                pieceNumber++;
            }
        }
        return allLegalMoves;
    }

    public boolean isCheck(char checker){ //Returns whether the checker is giving a check to the other player.

        boolean check = false;
        char enemyKing;

        if (checker == 'W') {
            enemyKing = 'k';
        }
        else {
            enemyKing = 'K';
        }

        for (Square square : Squares) {
            if (square.squarePiece != null && square.squarePiece.name == enemyKing) {
                targetSquare = square.position;
            }
        }

        for (ArrayList<Integer> move : getAllMoves(checker)) {
            if (move.contains(targetSquare)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public boolean isMate(char mater){//Returns if the mater is mating the opponent.
        if (!isCheck(mater)){ //No check means no mate
            return false;
        }
        char opponent;
        if (mater == 'B'){
            opponent = 'W';
        }else {
            opponent = 'B';
        }
        for (ArrayList<Integer> move : getAllMoves(opponent)){
            for (int i = 1; i < move.size();i++) {
                if (Squares[move.get(i)].squarePiece != null){
                    if (!(Squares[move.get(i)].squarePiece.name == 'K' ||
                            Squares[move.get(i)].squarePiece.name == 'k')) { //not a king
                        Piece tempPiece = Squares[move.get(i)].squarePiece; //temporarily moves a piece from the end move position to safety
                        makeTempMove(move.get(0), move.get(i)); //moves the piece
                        if (!isCheck(mater)) {
                            makeTempMove(move.get(i), move.get(0));
                            Squares[move.get(i)].squarePiece = tempPiece; //Undoes the moves
                            return false;
                        }
                        makeTempMove(move.get(i), move.get(0));
                        Squares[move.get(i)].squarePiece = tempPiece; //Undoes the moves
                    }
                }else{
                    makeTempMove(move.get(0), move.get(i)); //moves the piece
                    if (!isCheck(mater)) {
                        makeTempMove(move.get(i), move.get(0));//Undoes the move
                        return false;
                    }
                    makeTempMove(move.get(i), move.get(0));//Undoes the move
                }
            }
        }
        return true;
    }
}
