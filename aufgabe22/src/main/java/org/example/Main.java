package org.example;

public class Main {
    public static final int CARS = 10;
    public static final int PARKING_SPOTS = 5;
    public static final int ROUNDS = 100;

    public static void main(String[] args) {
        ParkingHouse parkingHouse = new ParkingHouse(PARKING_SPOTS);
        Car[] cars = new Car[CARS];
        for (int i = 0; i < CARS; i++) {
            cars[i] = new Car(parkingHouse, i);
            cars[i].start();
        }

        for (int i = 0; i < CARS; i++) {
            try {
                cars[i].join();
            } catch (InterruptedException e) {}
        }

        System.out.println("Done.");
    }
}