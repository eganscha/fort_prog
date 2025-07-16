package org.example;

public class CustomMutex {
    private Thread owner;

    CustomMutex() {}

    public synchronized void acquire() throws InterruptedException {
        while (owner != null && owner != Thread.currentThread()) {
            wait();
        }
        owner = Thread.currentThread();
    }

    public synchronized void release() {
        if (Thread.currentThread() != owner) {
            throw new IllegalCallerException();
        }
        owner = null;
        notifyAll();
    }
}
