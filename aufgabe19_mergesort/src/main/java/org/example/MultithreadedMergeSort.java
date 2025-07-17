package org.example;

import java.util.Arrays;

public class MultithreadedMergeSort extends Thread {
    private int[] a;
    public MultithreadedMergeSort(int[] a) {
        this.a = a;
    }

    @Override
    public void run() {
        int n = a.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;

        MultithreadedMergeSort t_left = new MultithreadedMergeSort(Arrays.copyOfRange(a, 0, mid));
        MultithreadedMergeSort t_right = new MultithreadedMergeSort(Arrays.copyOfRange(a, mid, n));
        t_left.start();
        t_right.start();
        try {
            t_left.join();
            t_right.join();
        } catch (InterruptedException e) {}

        merge(t_left.a, t_right.a, this.a);
    }

    private void merge(int[] left, int[] right, int[] original) {
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
