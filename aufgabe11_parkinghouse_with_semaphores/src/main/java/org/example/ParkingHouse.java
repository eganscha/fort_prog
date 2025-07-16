package org.example;

import java.util.concurrent.Semaphore;

public class ParkingHouse {
    private Semaphore semCapacity;

    public ParkingHouse(int capacity) {
        semCapacity = new Semaphore(capacity);
    }

    public void enter() throws InterruptedException {
        semCapacity.acquire();
    }

    public void leave() {
        semCapacity.release();
    }

    public int getSpots() {
        return semCapacity.availablePermits();
    }
}
