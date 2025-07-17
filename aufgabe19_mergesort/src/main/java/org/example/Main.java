package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 55, 37, 18, 12 };
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        MultithreadedMergeSort multithreadedMergeSort = new MultithreadedMergeSort(array);
        multithreadedMergeSort.start();
        try {
            multithreadedMergeSort.join();
        } catch (InterruptedException e) {}

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}