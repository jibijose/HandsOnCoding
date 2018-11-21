package com.test.dsalg.dfs;

//https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/

public class RottenOrangesTime {

    int M = 0;
    int N = 0;
    int numOfSweeps = 0;
    /*int[][] graph = new int[][]
            {
                    {2, 1, 0, 2, 1},
                    {1, 0, 1, 2, 1},
                    {1, 0, 0, 2, 1}
            };*/
    int[][] graph = new int[][]
            {
                    {2, 1, 0, 2, 1},
                    {0, 0, 1, 2, 1},
                    {1, 0, 0, 2, 1}
            };

    public static void main(String[] args) {
        RottenOrangesTime rottenOrangesTime = new RottenOrangesTime();
        System.out.println("TIME FRAMES = " + rottenOrangesTime.calculateTimeRequired());
    }

    private int calculateTimeRequired() {
        M = graph.length;
        if (graph.length > 0) {
            N = graph[0].length;
        }
        sweepOneTimeFrame(graph);
        if (!checkAllRotten()) {
            return -1;
        }
        return numOfSweeps;
    }

    private boolean checkAllRotten() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void sweepOneTimeFrame(int[][] graph) {
        int[][] graphAfterSweep = cloneArray(graph);
        boolean sweeped = false;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 0 || graph[i][j] == 2) {
                    continue;
                }
                if (checkIfNeighbourRotten(i, j, graph)) {
                    //System.out.println("Marking as rotten " + i + " " + j);
                    sweeped = true;
                    graphAfterSweep[i][j] = 2;
                }
            }
        }

        if (sweeped) {
            numOfSweeps++;
            sweepOneTimeFrame(graphAfterSweep);
        }
    }

    private boolean checkIfNeighbourRotten(int i, int j, int[][] graphTimed) {
        if (findRight(i, j, graphTimed) == 2 || findLeft(i, j, graphTimed) == 2
                || findTop(i, j, graphTimed) == 2 || findBottom(i, j, graphTimed) == 2) {
            return true;
        }
        return false;
    }

    private int findRight(int i, int j, int[][] graphTimed) {
        if (j + 1 >= N) {
            return -1;
        }
        return graphTimed[i][j + 1];
    }

    private int findLeft(int i, int j, int[][] graphTimed) {
        if (j - 1 < 0) {
            return -1;
        }
        return graphTimed[i][j - 1];
    }

    private int findTop(int i, int j, int[][] graphTimed) {
        if (i - 1 < 0) {
            return -1;
        }
        return graphTimed[i - 1][j];
    }

    private int findBottom(int i, int j, int[][] graphTimed) {
        if (i + 1 >= M) {
            return -1;
        }
        return graphTimed[i + 1][j];
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
