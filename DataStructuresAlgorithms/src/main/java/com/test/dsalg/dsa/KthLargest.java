package com.test.dsalg.dsa;

import java.util.PriorityQueue;

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek(); // K-th largest
    }

    public static void main(String[] args) {
        int[] initial = {4, 5, 8, 2};
        KthLargest kth = new KthLargest(3, initial);

        System.out.println(kth.add(3));  // → 4
        System.out.println(kth.add(5));  // → 5
        System.out.println(kth.add(10)); // → 5
        System.out.println(kth.add(9));  // → 8
        System.out.println(kth.add(4));  // → 8
    }
}
