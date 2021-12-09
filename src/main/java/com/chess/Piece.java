package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Piece {

    Piece(String pieceType){
        switch (pieceType) {
            case "WRook" -> makeWRook();
            case "BRook" -> makeBRook();
            case "WKnight" -> makeWKnight();
            case "BKnight" -> makeBKnight();
            case "WBishop" -> makeWBishop();
            case "BBishop" -> makeBBishop();
            case "WQueen" -> makeWQueen();
            case "BQueen" -> makeBQueen();
            case "WKing" -> makeWKing();
            case "BKing" -> makeBKing();
            case "WPawn" -> makeWPawn();
            case "BPawn" -> makeBPawn();
            default -> {
            }
        }
    }
    String name;
    boolean isWhite;
    int[] movePattern;
    int[] specialMoves = {}; //Castling, pawn captures, double pawn move
    boolean moveRecursion;
    Image imgPiece;
    ImageView imgViewPiece;

    private void makeWRook(){
        name = "WR";
        isWhite = true;
        movePattern = new int[] {-1,8,1,-8};
        moveRecursion = true;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\WR.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBRook(){
        name = "BR";
        isWhite = false;
        movePattern = new int[] {-1,8,1,-8};
        moveRecursion = true;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\BR.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKnight(){
        name = "WN";
        isWhite = true;
        movePattern = new int[] {6,15,17,10,-6,-10,-17,-15,-10};
        moveRecursion = false;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\WN.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKnight() {
        name = "BN";
        isWhite = false;
        movePattern = new int[]{-6, 15, 17, 10, -6, -10, -17, -15, -10};
        moveRecursion = false;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\BN.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWBishop(){
        name = "WB";
        isWhite = true;
        movePattern = new int[]{7, 9, -7, -9};
        moveRecursion = true;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\WB.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBBishop(){
        name = "BB";
        isWhite = false;
        movePattern = new int[]{7, 9, -7, -9};
        moveRecursion = true;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\BB.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWQueen(){
        name = "WQ";
        isWhite = true;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        moveRecursion = true;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\WQ.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBQueen(){
        name = "BQ";
        isWhite = false;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        moveRecursion = true;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\BQ.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKing(){
        name = "WK";
        isWhite = true;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\WK.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKing(){
        name = "BK";
        isWhite = false;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\BK.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWPawn(){
        name = "Wp";
        isWhite = true;
        movePattern = new int[]{8};
        specialMoves = new int[]{16,7,9};
        moveRecursion = false;
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\WP.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBPawn(){
        name = "Bp";
        isWhite = false;
        movePattern = new int[]{-8};
        specialMoves = new int[]{-16,-7,-9};
        imgPiece = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("Images\\BP.gif")));
        imgViewPiece = new ImageView(imgPiece);
    }

}
