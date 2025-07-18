package org.example;

public class ParkingHouse {
    private int capacity;
    private int nextWaitingNumber;
    private int nextEnteringNumber;

    public ParkingHouse(int capacity) {
        this.capacity = capacity;
        this.nextWaitingNumber = 0;
        this.nextEnteringNumber = 0;
    }

    public synchronized void enter() {
        int myWaitNumber = nextWaitingNumber++;
        while (capacity <= 0 || myWaitNumber != nextEnteringNumber) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        capacity--;
        nextEnteringNumber++;
        notifyAll();
    }

    public synchronized void leave() {
        capacity++;
        notifyAll();
    }
}
