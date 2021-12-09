package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Piece {

    Piece(char pieceType){
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
    char color = Character.MIN_VALUE;
    int[][] movePattern;//{Change in columns, change in rows}
    int[] specialMoves = {}; //Castling, pawn captures, double pawn move
    boolean moveRecursion;
//    Image imgPiece = new Image(Objects.requireNonNull(
//            getClass().getResourceAsStream("/src/main/Images/BR.gif")));
//    ImageView imgViewPiece;

    private void makeWRook(){
        name = 'R';
        color = 'W';
        movePattern = new int[][] {{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\WR.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBRook(){
        name = 'r';
        color = 'B';
        movePattern = new int[][] {{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\BR.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKnight(){
        name = 'N';
        color = 'W';
        movePattern = new int[][] {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        moveRecursion = false;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\WN.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKnight() {
        name = 'n';
        color = 'B';
        movePattern = new int[][] {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        moveRecursion = false;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\BN.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWBishop(){
        name = 'B';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1}};
        moveRecursion = true;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\WB.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBBishop(){
        name = 'b';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1}};
        moveRecursion = true;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\BB.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWQueen(){
        name = 'Q';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\WQ.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBQueen(){
        name = 'q';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\BQ.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKing(){
        name = 'K';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\WK.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKing(){
        name = 'k';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\BK.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWPawn(){
        name = 'P';
        color = 'W';
        movePattern = new int[][]{{0,1}};
        specialMoves = new int[]{16,7,9};
        moveRecursion = false;
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\WP.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBPawn(){
        name = 'p';
        color = 'B';
        movePattern = new int[][]{{0,-1}};
        specialMoves = new int[]{-16,-7,-9};
//        imgPiece = new Image(Objects.requireNonNull(
//                getClass().getResourceAsStream("Images\\BP.gif")));
//        imgViewPiece = new ImageView(imgPiece);
    }

}
