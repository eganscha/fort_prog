package org.example.counter;

public abstract class Counter extends Thread {
    protected static final int N = 2_000_000;
    public static volatile int val = 0;

    protected static volatile boolean flag0 = false;
    protected static volatile boolean flag1 = false;
    protected static volatile int turn;

    protected void increment() {
        val++;
    }

    @Override
    public abstract void run();
}