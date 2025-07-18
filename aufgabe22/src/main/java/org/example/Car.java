package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Car extends Thread {
    private ParkingHouse parkingHouse;
    private int ms_drivingAroundDuration;
    private int ms_parkingDuration;
    private int id;

    public Car(ParkingHouse parkingHouse, int id) {
        this.parkingHouse = parkingHouse;
        this.ms_drivingAroundDuration = ThreadLocalRandom.current().nextInt(1, 100);
        this.ms_parkingDuration = ThreadLocalRandom.current().nextInt(1, 400);
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.ROUNDS; i++) {
            try {
                // Car drives around before entering the parking house
                System.out.println("Car [" + id + "] driving around for (" + ms_drivingAroundDuration + "ms)");
                sleep(ms_drivingAroundDuration);

                // Enter
                System.out.println("Car [" + id + "] attempts to enter the parkingHouse");
                parkingHouse.enter();
                // Park
                System.out.println("Car [" + id + "] entered the parkingHouse for (" + ms_parkingDuration + "ms)");
                sleep(ms_parkingDuration);

                // Leave
                System.out.println("Car [" + id + "] leaving the parkingHouse");
                parkingHouse.leave();
            } catch (InterruptedException e) {}
        }
    }
}
