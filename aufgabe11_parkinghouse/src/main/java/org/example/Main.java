package org.example;

public class Main {
    private static final int CARS = 10;

    public static void main(String[] args) {
        ParkingHouse parkingHouse = new ParkingHouse();
        Car[] cars = new Car[CARS];

        for (int i = 0; i < CARS; i++) {
            cars[i] = new Car(parkingHouse, i);
            cars[i].start();
        }

        for (int i = 0; i < CARS; i++) {
            try {
                cars[i].join();
            } catch (InterruptedException e) { }
        }

        System.out.println("Done.");
    }
}