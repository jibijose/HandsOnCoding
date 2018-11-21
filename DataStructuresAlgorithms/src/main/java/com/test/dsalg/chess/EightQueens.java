package com.test.dsalg.chess;

public class EightQueens {

    public static int BOARDSIZE = 8;
    private int numOfSolutions = 0;

    int[][] boardStart = new int[BOARDSIZE][BOARDSIZE];
    int[] boardStateX = new int[BOARDSIZE];
    int[] boardStateY = new int[BOARDSIZE];
    int[] boardStateXR = new int[2 * BOARDSIZE - 1];
    int[] boardStateXL = new int[2 * BOARDSIZE - 1];

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.findSolutions();
    }

    private void findSolutions() {
        placeQueen(0);
    }

    private void placeQueen(int i) {
        if (i == BOARDSIZE) {
            numOfSolutions++;
            System.out.println("******************* SOLUTION " + numOfSolutions + " ******************************");
            printBoard();
            return;
        }

        for (int j = 0; j < BOARDSIZE; j++) {
            if (checkQueenAttacksEachOtherOptimized(i, j)) {
                continue;
            }
            prePlaceQueen(i, j);
            placeQueen(i + 1);
            postPlaceQueen(i, j);

        }
    }

    private void prePlaceQueen(int i, int j) {
        boardStart[i][j] = 1;
        boardStateX[i] = 1;
        boardStateY[j] = 1;
        boardStateXR[i - j + BOARDSIZE - 1] = 1;
        boardStateXL[i + j] = 1;
    }

    private void postPlaceQueen(int i, int j) {
        boardStart[i][j] = 0;
        boardStateX[i] = 0;
        boardStateY[j] = 0;
        boardStateXR[i - j + BOARDSIZE - 1] = 0;
        boardStateXL[i + j] = 0;
    }

    private void printBoard() {
        System.out.println("**************************************************************");
        for (int i = 0; i < BOARDSIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < BOARDSIZE; j++) {
                if (boardStart[i][j] < 10) {
                    System.out.print(" " + boardStart[i][j] + "|");
                } else {
                    System.out.print(boardStart[i][j] + "|");
                }
            }
            System.out.println();
        }
    }

    private boolean checkQueenAttacksEachOtherOptimized(int iQueen, int jQueen) {
        if (boardStateX[iQueen] == 1) {
            return true;
        }
        if (boardStateY[jQueen] == 1) {
            return true;
        }
        if (boardStateXR[iQueen - jQueen + BOARDSIZE - 1] == 1) {
            return true;
        }
        if (boardStateXL[iQueen + jQueen] == 1) {
            return true;
        }
        return false;
    }

}
