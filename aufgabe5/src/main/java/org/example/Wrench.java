package org.example;

import java.util.concurrent.Semaphore;

public class Wrench {
    private Semaphore semaphore = new Semaphore(1);

    public void take() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) { }
    }

    public void put() {
        semaphore.release();
    }
}
