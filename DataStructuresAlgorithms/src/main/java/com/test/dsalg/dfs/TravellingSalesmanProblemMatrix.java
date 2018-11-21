package com.test.dsalg.dfs;

//https://www.geeksforgeeks.org/traveling-salesman-problem-tsp-implementation/

import java.util.Stack;

public class TravellingSalesmanProblemMatrix {

    int startCity = 0;

    int minDistance = Integer.MAX_VALUE;
    Stack<Integer> maxPathCities = new Stack<>();
    Stack<Integer> maxPathRoads = new Stack<>();

    int currentMinDistance = 0;
    Stack<Integer> currentPathCities = new Stack<>();
    Stack<Integer> currentPathRoads = new Stack<>();

    int numOfCities = 4;
    int[][] graph = new int[][]
            {
                    {0, 10, 15, 20},
                    {10, 0, 35, 25},
                    {15, 35, 0, 30},
                    {20, 25, 30, 0}
            };
    boolean[] visited = new boolean[numOfCities];

    public static void main(String[] args) {
        TravellingSalesmanProblemMatrix travellingSalesmanProblemMatrix = new TravellingSalesmanProblemMatrix();
        travellingSalesmanProblemMatrix.solveTravel();
    }

    private void solveTravel() {
        for (int cityIndex = 0; cityIndex < numOfCities; cityIndex++) {
            visited[cityIndex] = true;
            startCity = cityIndex + 1;
            currentPathCities.add(cityIndex + 1);
            findMinDistanceUtil(cityIndex);
            visited[cityIndex] = false;
            currentPathCities.pop();
        }
    }

    private void findMinDistanceUtil(int cityIndex) {
        //System.out.println("Checking Min distance = " + currentMinDistance + " currentPathCities=" + currentPathCities + " currentPathRoads=" + currentPathRoads);
        if (cityIndex == startCity && currentPathCities.size() == numOfCities) {
            int totalTravelDistance = currentMinDistance + graph[currentPathCities.peek() - 1][cityIndex - 1];
            if (totalTravelDistance < minDistance) {
                minDistance = totalTravelDistance;
                currentPathRoads.add(graph[currentPathCities.peek() - 1][cityIndex - 1]);
                currentPathCities.add(cityIndex);
                System.out.println("Current Min distance = " + totalTravelDistance + " currentPathCities=" + currentPathCities + " currentPathRoads=" + currentPathRoads);
                currentPathRoads.pop();
                currentPathCities.pop();
            }
            return;
        }


        for (int roadIndex = 0; roadIndex < numOfCities; roadIndex++) {
            if (graph[cityIndex][roadIndex] != 0 && !visited[roadIndex]) {
                visited[roadIndex] = true;
                currentPathCities.add(roadIndex + 1);
                currentPathRoads.add(graph[cityIndex][roadIndex]);
                currentMinDistance = currentMinDistance + graph[cityIndex][roadIndex];
                findMinDistanceUtil(roadIndex);
                visited[roadIndex] = false;
                currentPathCities.pop();
                currentPathRoads.pop();
                currentMinDistance = currentMinDistance - graph[cityIndex][roadIndex];
            }
        }
    }
}
