package com.test.dsalg.dsa;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RotateArrayStreams {
    public static int[] rotate(int[] nums, int k) {
        int n = nums.length;
        int kRemainder = k % n; // In case k > n

        return IntStream.range(0, n).map(i -> nums[(i + kRemainder) % n]).toArray();
        /*int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = nums[(i + kRemainder) % n];
        }
        return result; */
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 12;

        System.out.println("Original array: " + Arrays.toString(nums));

        int[] rotated = rotate(nums, k);
        System.out.println("Rotated array: " + Arrays.toString(rotated));
    }
}

