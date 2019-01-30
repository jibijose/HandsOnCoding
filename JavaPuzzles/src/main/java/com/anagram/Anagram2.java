package com.anagram;

import java.util.ArrayList;
import java.util.List;

public class Anagram2 {

    private static int NUM_CHARS = 256;

    public List<List<String>> findAllAnagrams(List<String> values) {
        List<int[]> sortedValues = countChars(values);
        boolean[] pickedUp = new boolean[values.size()];

        List<List<String>> anagramsList = new ArrayList<List<String>>();
        for (int i = 0; i < sortedValues.size(); i++) {
            List<String> anagrams = new ArrayList<>();
            for (int j = i + 1; j < sortedValues.size(); j++) {
                if (!pickedUp[j] && checkCharCounts(sortedValues.get(i), sortedValues.get(j))) {
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

    private List<int[]> countChars(List<String> values) {
        List<int[]> sortedValues = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            char[] chars = values.get(i).toCharArray();
            int[] charCount = new int[NUM_CHARS];
            for (int j = 0; j < chars.length; j++) {
                charCount[chars[j]]++;
            }
            sortedValues.add(charCount);
        }

        return sortedValues;
    }

    private boolean checkCharCounts(int[] first, int[] second) {
        for (int i = 0; i < NUM_CHARS; i++) {
            if (first[i] != second[i]) {
                return false;
            }
        }
        return true;
    }

}
