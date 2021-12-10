package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Piece {

    Piece() {}

    char name = Character.MIN_VALUE;
    char color = Character.MIN_VALUE;
    int[][] movePattern;//{Change in columns, change in rows}
    int[] specialMoves = {}; //Castling, pawn captures, double pawn move
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
        Rook R = new Rook();
        R.name = 'R';
        R.color = 'W';
        R.imgPiece = new Image(new File("Images\\WR.gif").toURI().toString());
        R.imgViewPiece = new ImageView(R.imgPiece);
    }
    private void makeBRook(){
        Rook r = new Rook();
        r.name = 'r';
        r.color = 'B';
        r.imgPiece = new Image(new File("Images\\BR.gif").toURI().toString());
        r.imgViewPiece = new ImageView(r.imgPiece);
    }
    private void makeWKnight(){
        Knight N = new Knight();
        N.name = 'N';
        N.color = 'W';
        N.imgPiece = new Image(new File("Images\\WN.gif").toURI().toString());
        N.imgViewPiece = new ImageView(N.imgPiece);
    }
    private void makeBKnight() {
        Knight n = new Knight();
        n.name = 'n';
        n.color = 'B';
        n.imgPiece = new Image(new File("Images\\BN.gif").toURI().toString());
        n.imgViewPiece = new ImageView(n.imgPiece);
    }
    private void makeWBishop(){
        Bishop B = new Bishop();
        B.name = 'B';
        B.color = 'W';
        B.imgPiece = new Image(new File("Images\\WB.gif").toURI().toString());
        B.imgViewPiece = new ImageView(B.imgPiece);
    }
    private void makeBBishop(){
        Bishop b = new Bishop();
        b.name = 'b';
        b.color = 'B';
        b.imgPiece = new Image(new File("Images\\BB.gif").toURI().toString());
        b.imgViewPiece = new ImageView(b.imgPiece);
    }
    private void makeWQueen(){
        Queen Q = new Queen();
        Q.name = 'Q';
        Q.color = 'W';
        Q.imgPiece = new Image(new File("Images\\WQ.gif").toURI().toString());
        Q.imgViewPiece = new ImageView(Q.imgPiece);
    }
    private void makeBQueen(){
        Queen q = new Queen();
        q.name = 'q';
        q.color = 'B';
        q.imgPiece = new Image(new File("Images\\BQ.gif").toURI().toString());
        q.imgViewPiece = new ImageView(q.imgPiece);
    }
    private void makeWKing(){
        King K = new King();
        K.name = 'K';
        K.color = 'W';
        K.imgPiece = new Image(new File("Images\\WK.gif").toURI().toString());
        K.imgViewPiece = new ImageView(K.imgPiece);
    }
    private void makeBKing(){
        King k = new King();
        k.name = 'k';
        k.color = 'B';
        k.imgPiece = new Image(new File("Images\\BK.gif").toURI().toString());
        k.imgViewPiece = new ImageView(k.imgPiece);
    }
    private void makeWPawn(){
        Pawn P = new Pawn();
        P.name = 'P';
        P.color = 'W';
        P.imgPiece = new Image(new File("Images\\WP.gif").toURI().toString());
        P.imgViewPiece = new ImageView(P.imgPiece);
    }
    private void makeBPawn(){
        Pawn p = new Pawn();
        p.name = 'p';
        p.color = 'W';
        p.imgPiece = new Image(new File("Images\\BP.gif").toURI().toString());
        p.imgViewPiece = new ImageView(p.imgPiece);
    }

    protected void Moves() {}
}
