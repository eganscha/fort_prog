package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Car extends Thread {
    private final int ROUNDS = 5;
    private ParkingHouse parkingHouse;
    private int id;
    private int idleDuration;
    private int parkDuration;

    public Car(ParkingHouse parkingHouse, int id) {
        this.parkingHouse = parkingHouse;
        this.id = id;
        this.idleDuration = ThreadLocalRandom.current().nextInt(1, 5000);
        this.parkDuration = ThreadLocalRandom.current().nextInt(1, 5000);
    }

    @Override
    public void run() {
        for (int i = 0; i < ROUNDS; i++) {
            try {
                // Drive Around
                System.out.println("[Car " + id + "] driving around for " + idleDuration + "ms.");
                sleep(idleDuration);
                // Enter
                System.out.println("[Car " + id + "] attempts to enter parkingHouse with " + parkingHouse.getSpots() + " spots.");
                parkingHouse.enter();
                System.out.println("[Car " + id + "] enters the parkingHouse for " +  parkDuration + "ms.");
                sleep(parkDuration);
                // Leave
                System.out.println("[Car " + id + "] leaves the parkingHouse.");
                parkingHouse.leave();
            } catch (InterruptedException e) { }
        }
    }
}
