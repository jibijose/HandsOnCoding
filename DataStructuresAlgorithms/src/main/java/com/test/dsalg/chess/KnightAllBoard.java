package com.test.dsalg.chess;

import java.util.ArrayList;
import java.util.List;

public class KnightAllBoard {

    public static int BOARDSIZE = 8;

    private int numOfSolutions = 0;
    int numOfMoves = 0;
    int[][] board = new int[BOARDSIZE][BOARDSIZE];

    public static void main(String[] args) {
        KnightAllBoard knightAllBoard = new KnightAllBoard();
        knightAllBoard.findSolutions();
    }

    private void findSolutions() {
        for( int i=0; i<BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                placeKnight(i, j);
            }
        }
    }

    private void placeKnight(int i, int j) {

        numOfMoves++;
        board[i][j] = numOfMoves;

        if (numOfMoves == BOARDSIZE * BOARDSIZE) {
            numOfSolutions++;
            System.out.println("******************* SOLUTION " + numOfSolutions + " ******************************");
            printBoard();
            numOfMoves--;
            board[i][j] = 0;
            return;
        }

        List<Integer[]> nextMoves = findNextMoves(i, j);
        for (Integer[] nextMove : nextMoves) {
            placeKnight(nextMove[0], nextMove[1]);
        }

        numOfMoves--;
        board[i][j] = 0;
    }

    private void printBoard() {
        for (int i = 0; i < BOARDSIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < BOARDSIZE; j++) {
                if (board[i][j] < 10) {
                    System.out.print(" " + board[i][j] + "|");
                } else {
                    System.out.print(board[i][j] + "|");
                }
            }
            System.out.println();
        }
    }

    private static int[] KNIGHTONES = new int[]{-1, +1};
    private static int[] KNIGHTTWOS = new int[]{-2, +2};

    private List<Integer[]> findNextMoves(int i, int j) {
        List<Integer[]> nextMoves = new ArrayList<>();
        for (int x : KNIGHTONES) {
            for (int y : KNIGHTTWOS) {
                int xNext = i + x;
                int yNext = j + y;
                if (!isOutOfBoard(xNext, yNext) && board[xNext][yNext] == 0) {
                    nextMoves.add(new Integer[]{xNext, yNext});
                }
            }
        }
        for (int x : KNIGHTTWOS) {
            for (int y : KNIGHTONES) {
                int xNext = i + x;
                int yNext = j + y;
                if (!isOutOfBoard(xNext, yNext) && board[xNext][yNext] == 0) {
                    nextMoves.add(new Integer[]{xNext, yNext});
                }
            }
        }
        return nextMoves;
    }

    private boolean isOutOfBoard(int i, int j) {
        if (i < 0 || j < 0 || i >= BOARDSIZE || j >= BOARDSIZE) {
            return true;
        }
        return false;
    }

}
