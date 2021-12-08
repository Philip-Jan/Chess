package com.chess;

public class Board {
    private final int[][] board = new int[8][8];

    private void setBeginPosition() {
        for (int i=0; i < 8; i++){
            board[1][i] = 1;
            board[6][i] = 1;
        }

        board[0][0] = 2;
        board[0][1] = 3;
        board[0][2] = 4;
        board[0][3] = 6;
        board[0][4] = 5;
        board[0][5] = 4;
        board[0][6] = 3;
        board[0][7] = 2;

        board[7][0] = 2;
        board[7][1] = 3;
        board[7][2] = 4;
        board[7][3] = 6;
        board[7][4] = 5;
        board[7][5] = 4;
        board[7][6] = 3;
        board[7][7] = 2;

    }

    private void makeBoard() {
        setBeginPosition();
    }

    public int[][] getBoard() {
        return board;
    }
}
