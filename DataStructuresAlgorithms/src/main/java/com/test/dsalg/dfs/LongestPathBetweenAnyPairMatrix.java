package com.test.dsalg.dfs;

import java.util.Stack;

public class LongestPathBetweenAnyPairMatrix {

    int maxDistance = 0;
    Stack<Integer> maxPathCities = new Stack<>();
    Stack<Integer> maxPathRoads = new Stack<>();

    int currentMaxDistance = 0;
    Stack<Integer> currentPathCities = new Stack<>();
    Stack<Integer> currentPathRoads = new Stack<>();

    int numOfCities = 6;
    int[][] graph = new int[][]
            {
                    {0, 3, 0, 0, 0, 0},
                    {3, 0, 4, 0, 0, 2},
                    {0, 4, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 5},
                    {0, 2, 0, 6, 5, 0}
            };
    boolean[] visited = new boolean[numOfCities];

    public static void main(String[] args) {
        LongestPathBetweenAnyPairMatrix longestPathBetweenAnyPairMatrix = new LongestPathBetweenAnyPairMatrix();
        longestPathBetweenAnyPairMatrix.findMaxDistance();
    }

    private void findMaxDistance() {
        for (int cityIndex = 0; cityIndex < numOfCities; cityIndex++) {
            visited[cityIndex] = true;
            currentPathCities.add(cityIndex);
            findMaxDistanceUtil(cityIndex);
            visited[cityIndex] = false;
            currentPathCities.pop();
        }
    }

    private void findMaxDistanceUtil(int cityIndex) {
        System.out.println("Checking cityIndex=" + cityIndex + " CurrentMaxDistance=" + currentMaxDistance + " currentPathCities=" + currentPathCities + " currentPathRoads=" + currentPathRoads);
        boolean endCity = true;
        for (int roadIndex = 0; roadIndex < numOfCities; roadIndex++) {
            if (graph[cityIndex][roadIndex] != 0 && !visited[roadIndex]) {
                endCity = false;
                visited[roadIndex] = true;
                currentPathCities.add(roadIndex);
                currentPathRoads.add(graph[cityIndex][roadIndex]);
                currentMaxDistance = currentMaxDistance + graph[cityIndex][roadIndex];
                findMaxDistanceUtil(roadIndex);
                visited[roadIndex] = false;
                currentPathCities.pop();
                currentPathRoads.pop();
                currentMaxDistance = currentMaxDistance - graph[cityIndex][roadIndex];
            }
        }
        if (endCity && currentMaxDistance > maxDistance) {
            maxDistance = currentMaxDistance;
            System.out.println("Current Max distance = " + currentMaxDistance + " currentPathCities=" + currentPathCities + " currentPathRoads=" + currentPathRoads);
        }
    }
}
