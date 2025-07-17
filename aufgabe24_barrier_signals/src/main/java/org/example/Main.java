package org.example;

public class Main {
    public static void main(String[] args) {
        Barrier barrier = new Barrier(3);
        Worker[] workers = new Worker[3];

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker(barrier);
            workers[i].start();
        }

        for (int i = 0; i < workers.length; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {}
        }

        System.out.println("Done");
    }
}