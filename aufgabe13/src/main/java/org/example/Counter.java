package org.example;

import java.util.concurrent.Semaphore;

public class Counter implements Runnable {
    private static final int N = 2_000_000;
    private volatile int val;
    private Semaphore lock;

    public Counter() {
        val = 0;
        lock = new Semaphore(1);
    }

    public void increment() {
        try {
            lock.acquire();
        } catch (InterruptedException e) { }

        val++;

        lock.release();
    }

    public void sum_up() {
        for (int i = 0; i < N; i++) {
            increment();
        }
    }

    @Override
    public void run() {
        sum_up();
    }

    public int getVal() {
        return val;
    }
}