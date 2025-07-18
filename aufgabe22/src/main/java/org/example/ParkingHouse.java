package org.example;

public class ParkingHouse {
    int count;
    int capacity;

    public ParkingHouse(int capacity) {
        this.count = 0;
        this.capacity = capacity;
    }

    public synchronized void enter() {
        while (count >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        count++;
    }

    public synchronized void leave() {
        count--;
        notifyAll();
    }
}
