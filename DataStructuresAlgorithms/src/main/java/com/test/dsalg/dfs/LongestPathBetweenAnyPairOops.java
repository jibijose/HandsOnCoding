package com.test.dsalg.dfs;

import java.util.*;

public class LongestPathBetweenAnyPairOops {

    int numOfRoads = 0;
    int maxDistance = 0;
    Stack<City> maxPathCities = new Stack<>();
    Stack<Road> maxPathRoads = new Stack<>();

    int currentMaxDistance = 0;
    Stack<City> currentPathCities = new Stack<>();
    Stack<Road> currentPathRoads = new Stack<>();

    List<City> cities = new ArrayList<>();

    public static void main(String[] args) {
        LongestPathBetweenAnyPairOops longestPathBetweenAnyPairOops = new LongestPathBetweenAnyPairOops();
        longestPathBetweenAnyPairOops.readData();
        longestPathBetweenAnyPairOops.findMaxDistance();
        longestPathBetweenAnyPairOops.printMaxDistance();
    }

    private void printMaxDistance() {
        System.out.println("***********************************************************************************************************************");
        System.out.println("Max distance = " + maxDistance + " for cities " + maxPathCities + " distances " + maxPathRoads);
    }

    private void findMaxDistance() {
        for (City city : cities) {
            currentPathCities.add(city);
            findMaxDistanceUtil(city);
            currentPathCities.pop();
        }
    }

    private void findMaxDistanceUtil(City city) {
        //System.out.println("Checking city=" + city.cityCode + " maxDistance=" + maxDistance + " currentMaxDistance=" + currentMaxDistance + " pathCities=" + pathCities);
        boolean endCity = true;

        for (Road road : city.roads) {
            if (currentPathRoads.contains(road)) {
                continue;
            }
            endCity = false;

            currentMaxDistance = currentMaxDistance + road.distance;
            currentPathCities.add(road.otherCity(city));
            currentPathRoads.add(road);

            findMaxDistanceUtil(road.otherCity(city));

            currentMaxDistance = currentMaxDistance - road.distance;
            currentPathCities.pop();
            currentPathRoads.pop();
        }

        if (endCity) {
            if (currentMaxDistance > maxDistance) {
                maxDistance = currentMaxDistance;
                maxPathCities = (Stack<City>) currentPathCities.clone();
                maxPathRoads = (Stack<Road>) currentPathRoads.clone();
                System.out.println("Current Max distance = " + currentMaxDistance + " for cities " + currentPathCities + " distances " + currentPathRoads);
            }
        }
    }

    private void readData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of roads");
        //numOfRoads = Integer.parseInt(scanner.nextLine());
        numOfRoads = 6;
        for (int roadIndex = 0; roadIndex < numOfRoads; roadIndex++) {
            //String roadData = scanner.nextLine();
            //populateRoadData(roadData);
        }
        populateRoadData("1 2 3");
        populateRoadData("2 3 4");
        populateRoadData("2 3 5");
        populateRoadData("6 3 4");
        populateRoadData("2 6 2");
        populateRoadData("2 5 3");
        populateRoadData("4 5 5");
    }

    private void populateRoadData(String roadData) {
        StringTokenizer stringTokenizer = new StringTokenizer(roadData, " ");
        int fromCityCode = Integer.parseInt(stringTokenizer.nextToken());
        int toCityCode = Integer.parseInt(stringTokenizer.nextToken());
        int roadDistance = Integer.parseInt(stringTokenizer.nextToken());

        City fromCity = findOrCreateCity(fromCityCode);
        City toCity = findOrCreateCity(toCityCode);
        Road road = new Road(roadDistance, fromCity, toCity);
        fromCity.addRoad(road);
        toCity.addRoad(road);
    }

    private City findOrCreateCity(int cityCode) {
        Optional<City> optionalFromCity = findOptionalCity(cityCode);
        City city = null;
        if (optionalFromCity.isPresent()) {
            city = optionalFromCity.get();
        } else {
            city = new City(cityCode);
            cities.add(city);
        }
        return city;
    }

    private Optional<City> findOptionalCity(int cityCode) {
        for (City city : cities) {
            if (city.cityCode == cityCode) {
                return Optional.of(city);
            }
        }
        return Optional.empty();
    }

    class City {

        City(int cityCode) {
            this.cityCode = cityCode;
        }

        Integer cityCode;
        List<Road> roads = new ArrayList<>();

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof City)) {
                return false;
            }
            City other = (City) obj;
            if (cityCode.equals(other.cityCode)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return cityCode.hashCode();
        }

        public void addRoad(Road road) {
            roads.add(road);
        }

        @Override
        public String toString() {
            return "City{" + cityCode + '}';
        }
    }

    class Road {

        Road(int distance, City fromCity, City toCity) {
            this.distance = distance;
            this.fromCity = fromCity;
            this.toCity = toCity;
        }

        int distance;
        City fromCity;
        City toCity;

        public City otherCity(City city) {
            if (city.equals(fromCity)) {
                return toCity;
            } else if (city.equals(toCity)) {
                return fromCity;
            } else {
                throw new RuntimeException("City " + city + " is unknown to road " + this);
            }
        }

        @Override
        public String toString() {
            return "Road{" + distance + '}';
        }
    }

}
