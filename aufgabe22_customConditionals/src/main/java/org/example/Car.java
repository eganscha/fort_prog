package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Car extends Thread {
    private ParkingHouse parkingHouse;
    private int id;
    private int ms_drivingAroundDuration;
    private int ms_parkingDuration;

    public Car(ParkingHouse parkingHouse, int id) {
        this.parkingHouse = parkingHouse;
        this.id = id;
        this.ms_drivingAroundDuration = ThreadLocalRandom.current().nextInt(1, 100);
        this.ms_parkingDuration = ThreadLocalRandom.current().nextInt(1, 400);
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.ROUNDS; i++) {
            try {
                // drive around
                System.out.println(id + " - driving around.");
                sleep(ms_drivingAroundDuration);
                // enter
                System.out.println(id + " - attempting to enter.");
                parkingHouse.enter();
                // park
                System.out.println(id + " - parking.");
                sleep(ms_parkingDuration);
                // leave
                System.out.println(id + " - leaving.");
                parkingHouse.leave();
            } catch (InterruptedException e) {}
        }
    }
}
