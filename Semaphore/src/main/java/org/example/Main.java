package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        // Semaphore Example
        Semaphore semaphore = new Semaphore(5);

        try {
            semaphore.acquire();
        } catch (InterruptedException e) { }

        semaphore.release();
        semaphore.release();

        System.out.println("Done");

        // Mutex Example (synchronized in Java)
    }
}