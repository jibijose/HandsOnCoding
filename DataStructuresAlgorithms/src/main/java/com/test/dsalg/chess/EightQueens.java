package com.test.dsalg.chess;

public class EightQueens {

    public static int BOARDSIZE = 8;
    private int numOfSolutions = 0;

    int[][] attackedStart = new int[BOARDSIZE][BOARDSIZE];
    int[][] boardStart = new int[BOARDSIZE][BOARDSIZE];

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.findSolutions();
    }

    private void findSolutions() {
        placeQueen(0, attackedStart);
    }

    private void placeQueen(int i, int[][] attacked) {
        if (i == BOARDSIZE) {
            numOfSolutions++;
            System.out.println("******************* SOLUTION " + numOfSolutions + " ******************************");
            printBoard();
            return;
        }

        for (int j = 0; j < BOARDSIZE; j++) {
            //System.out.println("CHECKING [" + i + "," + j + "]");
            //printBoard();
            if (checkQueenAttacksEachOther(i, j)) {
                //System.out.println("QUEENS ATTACK EACH OTHER!!!");
                continue;
            }
            int[][] newAttacked = updateAttacked(i, j, attacked);
            boardStart[i][j] = 1;
            //System.out.println("PLACED QUEEN [" + i + "," + j + "]");
            //printAttack(newAttacked);
            placeQueen(i + 1, newAttacked);
            boardStart[i][j] = 0;
            //System.out.println("REMOVED QUEEN [" + i + "," + j + "]");
        }
    }

    private void printBoard() {
        System.out.println("**************************************************************");
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                System.out.print(boardStart[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printAttack(int[][] attacked) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                System.out.print(attacked[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean checkQueenAttacksEachOther(int iQueen, int jQueen) {
        for (int i = 0; i < BOARDSIZE; i++) {
            if (boardStart[i][jQueen] == 1) {
                return true;
            }
        }
        for (int j = 0; j < BOARDSIZE; j++) {
            if (boardStart[iQueen][j] == 1) {
                return true;
            }
        }
        for (int index = 0; index < BOARDSIZE; index++) {
            if (iQueen + index < BOARDSIZE && jQueen + index < BOARDSIZE) {
                if (boardStart[iQueen + index][jQueen + index] == 1) {
                    return true;
                }
            }
            if (iQueen - index >= 0 && jQueen + index < BOARDSIZE) {
                if (boardStart[iQueen - index][jQueen + index] == 1) {
                    return true;
                }
            }
            if (iQueen + index < BOARDSIZE && jQueen - index >= 0) {
                if (boardStart[iQueen + index][jQueen - index] == 1) {
                    return true;
                }
            }
            if (iQueen - index >= 0 && jQueen - index >= 0) {
                if (boardStart[iQueen - index][jQueen - index] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] updateAttacked(int iQueen, int jQueen, int[][] attacked) {
        int[][] newAttacked = cloneArray(attacked);
        for (int i = 0; i < BOARDSIZE; i++) {
            newAttacked[i][jQueen] = 1;
        }
        for (int j = 0; j < BOARDSIZE; j++) {
            newAttacked[iQueen][j] = 1;
        }
        for (int index = 0; index < BOARDSIZE; index++) {
            if (iQueen + index < BOARDSIZE && jQueen + index < BOARDSIZE) {
                newAttacked[iQueen + index][jQueen + index] = 1;
            }
            if (iQueen - index >= 0 && jQueen + index < BOARDSIZE) {
                newAttacked[iQueen - index][jQueen + index] = 1;
            }
            if (iQueen + index < BOARDSIZE && jQueen - index >= 0) {
                newAttacked[iQueen + index][jQueen - index] = 1;
            }
            if (iQueen - index >= 0 && jQueen - index >= 0) {
                newAttacked[iQueen - index][jQueen - index] = 1;
            }
        }
        return newAttacked;
    }

    private int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }
}
