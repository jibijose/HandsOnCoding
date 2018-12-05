package com.test.dsalg.array;

public class TwoArraysIdenticalMinSwapCount {

    public static void main(String[] args) {
        TwoArraysIdenticalMinSwapCount twoArraysIdenticalMinSwapCount = new TwoArraysIdenticalMinSwapCount();
        twoArraysIdenticalMinSwapCount.invoke();
    }

    private void invoke() {
        int[] arrA = {3, 6, 4, 8};
        int[] arrB = {4, 6, 8, 3};
        System.out.println("ARRAY A");
        printArray(arrA);
        System.out.println("*****************************");
        System.out.println("ARRAY B");
        printArray(arrB);
        System.out.println("*****************************");
        System.out.println("Minimum = " + minimumSwap(arrA, arrB));
    }


    private int minimumSwap(int[] arrA, int[] arrB) {
        int swapCount = 0;

        if (arrA.length != arrB.length) {
            throw new RuntimeException("Array size are different");
        }

        for (int i = 0; i < arrA.length; i++) {
            boolean swapped = false;
            printArray(arrB);
            if (arrA[i] != arrB[i]) {
                for (int j = i; j < arrB.length; j++) {
                    if (arrA[i] == arrB[j]) {
                        //  Swap...
                        swapCount++;
                        int temp = arrB[i];
                        arrB[i] = arrB[j];
                        arrB[j] = temp;
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) {
                    throw new RuntimeException("Couldn't find matching element for A array " + arrA[i]);
                }
            }
        }

        return swapCount;
    }

    private void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

}
