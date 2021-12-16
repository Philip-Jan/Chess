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
    private final int pieceImg;
    private String str;
    Image imgPiece;
    ImageView imgViewPiece;

    Piece(char maybePieceType, int pieceImg){

        this.pieceImg = pieceImg;
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\WR.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\WR.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\WR.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\WR.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\WR.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\WR.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\BR.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\BR.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\BR.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\BR.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\BR.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\BR.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\WN.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\WN.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\WN.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\WN.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\WN.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\WN.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\BN.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\BN.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\BN.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\BN.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\BN.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\BN.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\WB.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\WB.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\WB.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\WB.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\WB.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\WB.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\BB.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\BB.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\BB.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\BB.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\BB.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\BB.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\WQ.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\WQ.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\WQ.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\WQ.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\WQ.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\WQ.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\BQ.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\BQ.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\BQ.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\BQ.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\BQ.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\BQ.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\WK.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\WK.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\WK.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\WK.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\WK.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\WK.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\BK.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\BK.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\BK.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\BK.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\BK.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\BK.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\WP.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\WP.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\WP.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\WP.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\WP.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\WP.png").toURI().toString();
        }
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
        switch (pieceImg) {
            case 0 -> str = new File("src\\main\\Images\\Cardinal\\BP.png").toURI().toString();
            case 1 -> str = new File("src\\main\\Images\\Chessnut\\BP.png").toURI().toString();
            case 2 -> str = new File("src\\main\\Images\\Dubrovny\\BP.png").toURI().toString();
            case 3 -> str = new File("src\\main\\Images\\Fresca\\BP.png").toURI().toString();
            case 4 -> str = new File("src\\main\\Images\\Horsey\\BP.png").toURI().toString();
            case 5 -> str = new File("src\\main\\Images\\Kosal\\BP.png").toURI().toString();
        }
        String string = str.substring(6);
        imgPiece = new Image(string);
        imgViewPiece = new ImageView(imgPiece);
        imgViewPiece.setFitWidth(50);
        imgViewPiece.setFitHeight(50);
        imgViewPiece.setPreserveRatio(true);
    }
}
