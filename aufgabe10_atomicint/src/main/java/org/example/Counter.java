package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter implements Runnable {
    private static final int N = 2_000_000;
    private AtomicInteger val = new AtomicInteger(0);

    public void increment() {
        val.incrementAndGet();
    }

    public void sum_up() {
        for (int i = 0; i < N; i++) {
            increment();
        }
    }

    public int getValue() {
        return val.get();
    }

    // Damit der andere Thread sum_up() ausführen kann.
    @Override
    public void run() {
        sum_up();
    }
}