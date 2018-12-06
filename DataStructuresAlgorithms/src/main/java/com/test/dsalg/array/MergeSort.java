package com.test.dsalg.array;

// https://www.geeksforgeeks.org/merge-sort/

public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.invoke();
    }

    private void invoke() {
        int[] arr = {3, 6, 4, 8, 1, 2, 10, 7, 5, 9};
        //int[] arr = {38, 27, 43, 3, 9, 82, 10};
        //int[] arr = {30, 10, 40, 20};
        printArray(arr);
        System.out.println("*******************************************");
        mergeSort(arr, 0, arr.length / 2, arr.length - 1);
        System.out.println("*******************************************");
        printArray(arr);
    }


    private void mergeSort(int[] arr, int left, int middle, int right) {
        if ( left > middle || middle > right ) {
            return;
        }
        printArray("MERGE   ", arr, left, middle, right);
        if (left == right) {
            return;
        }

        mergeSort(arr, left, (left + middle) / 2, middle);
        mergeSort(arr, middle + 1, (middle + 1 + right) / 2, right);
        combine(arr, left, middle, right);
    }

    private void combine(int[] arr, int left, int middle, int right) {
        printArray("COMBINE ", arr, left, middle, right);
        int leftIndex = left;
        int rightIndex = middle + 1;

        while (leftIndex < rightIndex && rightIndex <= right) {
            if (arr[leftIndex] < arr[rightIndex]) {
                leftIndex++;
            } else if (arr[leftIndex] > arr[rightIndex]) {
                int temp = arr[rightIndex];
                for (int i = rightIndex; i > leftIndex; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[leftIndex] = temp;
                rightIndex++;
                //leftIndex++;
            }
        }
        printArray("AFTER   ", arr, left, middle, right);
    }

    private void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    private void printArray(String mesg, int[] arr, int left, int middle, int right) {
        System.out.print(mesg + "  LEFT=" + left + " MIDDLE=" + middle + " RIGHT=" + right + "   ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
