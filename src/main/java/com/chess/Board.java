package com.chess;

public class Board {

    Board () {
        makeBoard();
    }

    private final String[][] board = new String[8][8];

    private void setBeginPosition() {
        for (int i=0; i < 8; i++){
            board[1][i] = "Wp";
            board[6][i] = "Bp";
        }

        board[0][0] = "WT";
        board[0][1] = "WP";
        board[0][2] = "WB";
        board[0][3] = "WD";
        board[0][4] = "WK";
        board[0][5] = "WB";
        board[0][6] = "WP";
        board[0][7] = "WT";

        board[7][0] = "BT";
        board[7][1] = "BP";
        board[7][2] = "BB";
        board[7][3] = "BD";
        board[7][4] = "BK";
        board[7][5] = "BB";
        board[7][6] = "BP";
        board[7][7] = "BT";

    }

    private void makeBoard() {
        setBeginPosition();
    }

    public String[][] getBoard() {
        return board;
    }
}
