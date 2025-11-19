package com.test.dsalg.dsa;

public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];       // overall max
        int currentSum = nums[0];   // max ending here

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-150, 100, -3, 4, -1, 2, 1, -5, 4};
        //int[] nums = {1, -30, 4, -1, 20, 1, 50, 1};
        System.out.println("Maximum subarray sum: " + maxSubArray(nums)); // 6
    }
}
