package org.example;

public class ParkingHouse {
    CountingSemaphore countingSemaphore;

    public ParkingHouse(int capacity) {
        this.countingSemaphore = new CountingSemaphore(capacity);
    }

    public void enter() {
        try {
            countingSemaphore.acquire();
        } catch (InterruptedException e) { }
    }

    public void leave() {
        countingSemaphore.release();
    }
}
