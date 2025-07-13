package org.example;

public class Worker implements Runnable {
    private static final int WORKER_THREAD_SLEEP_MS = 100;

    @Override
    public void run() {
        try {
            Thread.sleep(WORKER_THREAD_SLEEP_MS);
        } catch (InterruptedException e) {
            // EMPTY
        }

        System.out.println("Hello from Thread: " + Thread.currentThread().getName());
    }
}
