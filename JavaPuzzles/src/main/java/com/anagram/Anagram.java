package com.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anagram {

    public List<List<String>> findAllAnagrams(List<String> values) {
        List<String> sortedValues = sortStrings(values);
        boolean[] pickedUp = new boolean[values.size()];


        List<List<String>> anagramsList = new ArrayList<List<String>>();
        for (int i = 0; i < sortedValues.size(); i++) {
            List<String> anagrams = new ArrayList<>();
            for (int j = i + 1; j < sortedValues.size(); j++) {
                if (!pickedUp[j] && sortedValues.get(i).equals(sortedValues.get(j))) {
                    anagrams.add(values.get(j));
                    pickedUp[j] = true;
                }
            }
            if (anagrams.size() > 0) {
                anagrams.add(values.get(i));
                anagramsList.add(anagrams);
            }
        }

        return anagramsList;
    }

    private List<String> sortStrings(List<String> values) {
        List<String> sortedValues = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            char[] chars = values.get(i).toCharArray();
            Arrays.sort(chars);
            sortedValues.add(new String(chars));
        }
        return sortedValues;
    }
}
