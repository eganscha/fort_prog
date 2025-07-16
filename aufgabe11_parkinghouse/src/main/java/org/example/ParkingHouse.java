package org.example;

public class ParkingHouse {
    int spots = 5;

    public synchronized void enter() {
        while(spots < 1) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        spots--;
    }

    public synchronized void leave() {
        spots++;
        notify();
    }

    public synchronized int getSpots() {
        return spots;
    }
}
