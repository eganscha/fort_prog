package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static final int N = 5;
    public static final int ROUNDS = 100;

    public static void main(String[] args) {
        Semaphore[] wrenches = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            wrenches[i] = new Semaphore(1);
        }

        Mechanic[] mechanics = new Mechanic[5];
        for (int i = 0; i < N; i++) {
            mechanics[i] = new Mechanic(wrenches, i);
            mechanics[i].start();
        }

        try {
            for (int i = 0; i < N; i++) {
                mechanics[i].join();
            }
        } catch (InterruptedException e) {}

        System.out.println("Done.");
    }
}