package org.example;

public class Worker extends Thread {
    Barrier barrier;

    public Worker(Barrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("Waiting to pass Barrier.");
        try {
            barrier.barrierWait();
        } catch (InterruptedException e) { }
        System.out.println("Barrier passed.");
    }
}
