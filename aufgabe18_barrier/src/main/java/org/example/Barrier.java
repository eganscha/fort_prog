package org.example;

import java.util.concurrent.Semaphore;

public class Barrier {
    private Semaphore semaphore;
    private int count;
    private int target;

    public Barrier(int target) {
        this.semaphore = new Semaphore(0);
        this.count = 0;
        this.target = target;
    }

    public synchronized void barrierWait() throws InterruptedException {
        count++;
        if (count == target) {
            // Release Barrier
            semaphore.release(count - 1); // (beim letzten wird nicht acquired)
        } else {
            semaphore.acquire();
        }
    }
}
