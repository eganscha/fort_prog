package org.example;

public class Counter implements Runnable {
    private static final int N = 2_000_000;
    public int val = 0;
    public void increment() {
        val++;
    }

    public void sum_up() {
        for (int i = 0; i < N; i++) {
            increment();
        }
    }

    // Damit der andere Thread sum_up() ausführen kann.
    @Override
    public void run() {
        sum_up();
    }
}