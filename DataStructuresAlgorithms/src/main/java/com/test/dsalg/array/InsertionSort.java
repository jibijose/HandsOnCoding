package com.test.dsalg.array;

// https://www.geeksforgeeks.org/insertion-sort/

public class InsertionSort {

    void sort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j >= 1 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void printArray(int arr[]) {
        System.out.println("***************************************************");
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6};

        InsertionSort ob = new InsertionSort();
        printArray(arr);
        ob.sort(arr);
        printArray(arr);
    }
}
