package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Piece {

    Piece(char maybePieceType){

        char pieceType = ' ';

        char[] pieceType1 = new char[]{
                'R', 'r', 'N', 'n', 'B', 'b', 'Q', 'q', 'K', 'k', 'P', 'p'
        };
        for (char value : pieceType1) {
            if (maybePieceType == value) {
                pieceType = maybePieceType;
                break;
            }
        }

        switch (pieceType) {
            case 'R' -> makeWRook();
            case 'r' -> makeBRook();
            case 'N' -> makeWKnight();
            case 'n' -> makeBKnight();
            case 'B' -> makeWBishop();
            case 'b' -> makeBBishop();
            case 'Q' -> makeWQueen();
            case 'q' -> makeBQueen();
            case 'K' -> makeWKing();
            case 'k' -> makeBKing();
            case 'P' -> makeWPawn();
            case 'p' -> makeBPawn();
            default -> {
            }
        }
    }

    char name = Character.MIN_VALUE;
    boolean isWhite;
    int[] movePattern;
    int[] specialMoves = {}; //Castling, pawn captures, double pawn move
    boolean moveRecursion;
    Image imgPiece;
    ImageView imgViewPiece;

    private void makeWRook(){
        name = 'R';
        isWhite = true;
        movePattern = new int[] {-1,8,1,-8};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\WR.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBRook(){
        name = 'r';
        isWhite = false;
        movePattern = new int[] {-1,8,1,-8};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BR.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKnight(){
        name = 'N';
        isWhite = true;
        movePattern = new int[] {6,15,17,10,-6,-10,-17,-15,-10};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WN.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKnight() {
        name = 'n';
        isWhite = false;
        movePattern = new int[]{-6, 15, 17, 10, -6, -10, -17, -15, -10};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\BN.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWBishop(){
        name = 'B';
        isWhite = true;
        movePattern = new int[]{7, 9, -7, -9};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\WB.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBBishop(){
        name = 'b';
        isWhite = false;
        movePattern = new int[]{7, 9, -7, -9};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BB.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWQueen(){
        name = 'Q';
        isWhite = true;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\WQ.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBQueen(){
        name = 'q';
        isWhite = false;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BQ.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKing(){
        name = 'K';
        isWhite = true;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WK.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKing(){
        name = 'k';
        isWhite = false;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\BK.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWPawn(){
        name = 'P';
        isWhite = true;
        movePattern = new int[]{8};
        specialMoves = new int[]{16,7,9};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WP.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBPawn(){
        name = 'p';
        isWhite = false;
        movePattern = new int[]{-8};
        specialMoves = new int[]{-16,-7,-9};
        imgPiece = new Image(new File("Images\\BP.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }

    public ImageView getImgViewPiece() {
        return imgViewPiece;
    }

}
