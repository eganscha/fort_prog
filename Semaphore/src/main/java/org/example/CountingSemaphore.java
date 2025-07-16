package org.example;

public class CountingSemaphore {
    private int value = 0;

    public CountingSemaphore(int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public synchronized void acquire() throws InterruptedException {
        while (value < 1) {
            wait();
        }
        value--;
    }

    public synchronized void release() {
        value++;
        notifyAll();
    }
}
