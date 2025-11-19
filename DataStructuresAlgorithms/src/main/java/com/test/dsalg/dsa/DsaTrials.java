package com.test.dsalg.dsa;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DsaTrials {

    public int[] findTargetSumInArray(int[] sourceArray, int sum) {
        int[] myReturnElements = null;

        Map<Integer, Integer> myValueMap
                = IntStream.range(0, sourceArray.length).boxed()
                .collect(Collectors.toMap(index -> sourceArray[index], index -> index));

        Integer firstSumValue = myValueMap.entrySet().stream()
                .filter(entrySet -> myValueMap.containsKey(sum - entrySet.getKey())).limit(1)
                .map(Map.Entry::getKey)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.isEmpty() ? null : list.get(0)));
        //.toArray(Integer[]::new);
        myReturnElements = new int[]{myValueMap.get(firstSumValue), myValueMap.get(sum - firstSumValue)};

        return myReturnElements;
    }
}
