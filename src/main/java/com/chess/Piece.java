package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Piece {

    char name = Character.MIN_VALUE;
    char color = Character.MIN_VALUE;
    int[][] movePattern;//{Change in columns, change in rows}
    int[][] specialMoves; //Castling, pawn captures, double pawn move
    boolean moveRecursion;
    Image imgPiece;
    ImageView imgViewPiece;

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
            default -> {}
        }
    }

    private void makeWRook(){
        name = 'R';
        color = 'W';
        movePattern = new int[][] {{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
        String str = new File("src\\main\\Images\\WR.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeBRook(){
        name = 'r';
        color = 'B';
        movePattern = new int[][] {{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
        String str = new File("src\\main\\Images\\BR.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeWKnight(){
        name = 'N';
        color = 'W';
        movePattern = new int[][] {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        moveRecursion = false;
        String str = new File("src\\main\\Images\\WN.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeBKnight() {
        name = 'n';
        color = 'B';
        movePattern = new int[][] {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        moveRecursion = false;
        String str = new File("src\\main\\Images\\BN.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeWBishop(){
        name = 'B';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1}};
        moveRecursion = true;
        String str = new File("src\\main\\Images\\WB.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeBBishop(){
        name = 'b';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1}};
        moveRecursion = true;
        String str = new File("src\\main\\Images\\BB.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeWQueen(){
        name = 'Q';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
        String str = new File("src\\main\\Images\\WQ.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeBQueen(){
        name = 'q';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        moveRecursion = true;
        String str = new File("src\\main\\Images\\BQ.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeWKing(){
        name = 'K';
        color = 'W';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        specialMoves = new int[][]{{-2,0},{2,0}};
        moveRecursion = false;
        String str = new File("src\\main\\Images\\WK.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeBKing(){
        name = 'k';
        color = 'B';
        movePattern = new int[][]{{-1,1},{1,1},{1,-1},{-1,-1},{-1,0},{1,0},{1,0},{0,-1}};
        specialMoves = new int[][]{{-2,0},{2,0}};
        moveRecursion = false;
        String str = new File("src\\main\\Images\\BK.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeWPawn(){
        name = 'P';
        color = 'W';
        movePattern = new int[][]{{0,1}};
        specialMoves = new int[][]{{0,2},{1,1},{-1,1}};
        moveRecursion = false;
        String str = new File("src\\main\\Images\\WP.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
    private void makeBPawn(){
        name = 'p';
        color = 'B';
        movePattern = new int[][]{{0,-1}};
        specialMoves = new int[][]{{0,-2},{1,-1},{-1,-1}};
        moveRecursion = false;
        String str = new File("src\\main\\Images\\BP.gif").toURI().toString();
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
}
