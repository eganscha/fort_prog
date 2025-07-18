package org.example;

public class CountingSemaphore {
    private int permits;

    public CountingSemaphore(int permits) {
        if (permits < 0) {
            throw new IllegalArgumentException();
        }
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits <= 0) {
            System.out.println("WAITING");
            wait();
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notifyAll();
    }
}
