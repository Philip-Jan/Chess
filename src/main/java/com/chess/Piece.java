package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
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
    String name = "";
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
        imgPiece = new Image(new File("Images\\WR.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBRook(){
        name = "BR";
        isWhite = false;
        movePattern = new int[] {-1,8,1,-8};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BR.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKnight(){
        name = "WN";
        isWhite = true;
        movePattern = new int[] {6,15,17,10,-6,-10,-17,-15,-10};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WN.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKnight() {
        name = "BN";
        isWhite = false;
        movePattern = new int[]{-6, 15, 17, 10, -6, -10, -17, -15, -10};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\BN.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWBishop(){
        name = "WB";
        isWhite = true;
        movePattern = new int[]{7, 9, -7, -9};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\WB.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBBishop(){
        name = "BB";
        isWhite = false;
        movePattern = new int[]{7, 9, -7, -9};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BB.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWQueen(){
        name = "WQ";
        isWhite = true;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\WQ.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBQueen(){
        name = "BQ";
        isWhite = false;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BQ.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKing(){
        name = "WK";
        isWhite = true;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WK.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKing(){
        name = "BK";
        isWhite = false;
        movePattern = new int[]{-1,7,8,9,1,-7,-8,-9};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\BK.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWPawn(){
        name = "Wp";
        isWhite = true;
        movePattern = new int[]{8};
        specialMoves = new int[]{16,7,9};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WP.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBPawn(){
        name = "Bp";
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
