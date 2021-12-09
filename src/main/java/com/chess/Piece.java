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
    char color = Character.MIN_VALUE;
    int[][] movePattern;//{Change in columns, change in rows}
    int[] specialMoves = {}; //Castling, pawn captures, double pawn move
    boolean moveRecursion;
    Image imgPiece;
    ImageView imgViewPiece;

    private void makeWRook(){
        name = 'R';
        color = 'W';
        movePattern = new int[][] {{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\WR.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBRook(){
        name = 'r';
        color = 'B';
        movePattern = new int[][] {{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BR.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKnight(){
        name = 'N';
        color = 'W';
        movePattern = new int[][] {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WN.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKnight() {
        name = 'n';
        color = 'B';
        movePattern = new int[][] {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\BN.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWBishop(){
        name = 'B';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1}};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\WB.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBBishop(){
        name = 'b';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1}};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BB.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWQueen(){
        name = 'Q';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\WQ.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBQueen(){
        name = 'q';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
        imgPiece = new Image(new File("Images\\BQ.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWKing(){
        name = 'K';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WK.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBKing(){
        name = 'k';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        specialMoves = new int[]{-2,2};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\BK.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeWPawn(){
        name = 'P';
        color = 'W';
        movePattern = new int[][]{{0,1}};
        specialMoves = new int[]{16,7,9};
        moveRecursion = false;
        imgPiece = new Image(new File("Images\\WP.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }
    private void makeBPawn(){
        name = 'p';
        color = 'B';
        movePattern = new int[][]{{0,-1}};
        specialMoves = new int[]{-16,-7,-9};
        imgPiece = new Image(new File("Images\\BP.gif").toURI().toString());
        imgViewPiece = new ImageView(imgPiece);
    }

    public ImageView getImgViewPiece() {
        return imgViewPiece;
    }

}
