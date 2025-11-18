package com.test.dsalg.javastream;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class MyJavaStreamChecks {
    public static void main(String args[]) {
        MyJavaStreamChecks myJavaStreamChecks = new MyJavaStreamChecks();

        List<Person> people = Arrays.asList(
                new Person("Alice", "New York", 25),
                new Person("Bob", "London", 30),
                new Person("Charlie", "New York", 30),
                new Person("David", "London", 25),
                new Person("Eve", "Paris", 35)
        );
        myJavaStreamChecks.doWork(people);
    }

    private void doWork(List<Person> people) {

        //Group by city
        Map<String, List<Person>> myMap1 = people.stream().collect(Collectors.groupingBy(Person::getCity));
        log.debug("" + myMap1);

        //Group by city
        Map<Person, List<Person>> myMap2 = people.stream().collect(Collectors.groupingBy(p -> p));
        log.debug("" + myMap2);

        //Group by city
        Map<Person, List<Person>> myMap3 = people.stream().collect(Collectors.groupingBy(p -> p, Collectors.toList()));
        log.debug("" + myMap3);

        //Group by city
        Map<String, List<Person>> myMap4 = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.toList()));
        log.debug("" + myMap4);

        //Group by city
        Map<String, Set<Person>> myMap5 = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.toSet()));
        log.debug("" + myMap5);

        //Map<String, Map<String, Integer>>
        //TreeMap<String, LinkedHashMap<String, Integer>>
        Map<String, List<Transaction>> testMap1 = new HashMap<>();
        Map<String, Map<String, Integer>> checkMap = populateSampleTransactions().stream()
                .collect(Collectors.groupingBy(Transaction::getCity))
                .entrySet().stream()
                //.flatMap(entry -> entry.getValue().stream().collect(Collectors.toMap(s -> s.)))
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().stream().collect(Collectors.groupingBy(Transaction::getTrader, TreeMap::new, Collectors.summingInt(Transaction::getAmount)))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, TreeMap::new));
        log.debug("" + checkMap);

        Map<String, Long> mapIpCounts = populateIPs().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> mapSortedIpCounts = mapIpCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
        log.debug("" + mapSortedIpCounts.entrySet().stream().limit(2).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private List<Transaction> populateSampleTransactions() {
        List<Transaction> transactions = List.of(
                new Transaction("Alice", "London", 300),
                new Transaction("Bob", "London", 150),
                new Transaction("Alice", "London", 120),
                new Transaction("Charlie", "London", 500),

                new Transaction("David", "NewYork", 700),
                new Transaction("Alice", "NewYork", 200),
                new Transaction("Frank", "NewYork", 400),

                new Transaction("Helen", "Paris", 350),
                new Transaction("Grace", "Paris", 300),
                new Transaction("Helen", "Paris", 150)
        );


        return transactions;
    }

    private List<String> populateIPs() {
        List<String> iPs = List.of("127.0.0.1", "192.168.0.1", "127.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.100", "192.168.0.100", "192.168.0.100", "192.168.0.100", "192.168.0.100");
        return iPs;
    }
}
