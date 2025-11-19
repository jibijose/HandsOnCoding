package com.test.dsalg.dsa;

import java.util.*;

public class WordBreak {

    public static boolean wordBreakNew(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {

            }
        }

        return true;
    }


    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {

        // Complex dictionary words
        List<String> dict = Arrays.asList(
                "machine",
                "learning",
                "deep",
                "neural",
                "network",
                "transformer",
                "model",
                "chatgpt",
                "language",
                "processing",
                "supercalifragilisticexpialidocious",
                "hyperparameter",
                "optimization"
        );

        // Complex input strings
        String[] tests = {
                "machinelearningmodel",
                "deepneuralnetwork",
                "languageprocessing",
                "supercalifragilisticexpialidocious",
                "hyperparameteroptimization"
        };

        // Measure time
        long start = System.nanoTime();

        for (int i = 0; i < 1; i++) {
            for (String s : tests) {
                boolean result = wordBreakNew(s, dict);
                if (!result) {
                    System.out.println("Unexpected FALSE on: " + s);
                }
            }
        }

        long end = System.nanoTime();

        long durationNs = end - start;
        double durationMs = durationNs / 1_000_000.0;

        System.out.println("Completed 1000000 iterations.");
        System.out.println("Total time (ns): " + durationNs);
        System.out.println("Total time (ms): " + durationMs);
    }
}

