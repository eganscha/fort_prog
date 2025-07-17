package org.example;

import java.util.Arrays;

public class MergeSort {

    private MergeSort() {}

    public static void mergeSort(int[] a) {
        int n = a.length;
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] left  = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, n);

        mergeSort(left);
        mergeSort(right);
        merge(left, right, a);
    }

    public static void merge(int[] left, int[] right, int[] original) {
        int i = 0;
        int l = 0;
        int r = 0;

        while(l < left.length && r < right.length) {
            if(left[l] <= right[r]) {
                original[i++] = left[l++];
            } else {
                original[i++] = right[r++];
            }
        }

        // Copy Left-Overs, only one will run
        while(l < left.length) {
            original[i++] = left[l++];
        }
        while(r < right.length) {
            original[i++] = right[r++];
        }
    }
}
