package org.example;

import java.util.concurrent.Semaphore;

public class Barrier {
    private int count;
    private int target;

    public Barrier(int target) {
        this.count = 0;
        this.target = target;
    }

    public synchronized void barrierWait() throws InterruptedException {
        count++;
        while (count < target) {
            wait();
        }
        notifyAll();
    }
}
