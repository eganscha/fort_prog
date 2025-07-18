package org.example;

public class Main {
    public static final int CARS = 10;
    public static final int PARKING_HOUSE_CAPACITY = 5;
    public static final int ROUNDS = 50;

    public static void main(String[] args) {
        ParkingHouse parkingHouse = new ParkingHouse(PARKING_HOUSE_CAPACITY);
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