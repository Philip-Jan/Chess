package com.chess;

public class Piece {
    String name;
    String color;
    int[][] movePattern;
    boolean moveRecursion;

    private void makeWRook(){
        name = "WR";
        color = "W";
        movePattern = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        moveRecursion = true;
    }
    private void makeBRook(){
        name = "BR";
        color = "B";
        movePattern = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        moveRecursion = true;
    }
}
