package org.example;

public class Main {

    public static void main(String[] args) {
        // Wrenches erzeugen
        Wrench[] wrenches = new Wrench[Mechanic.N];
        for (int i = 0; i < Mechanic.N; i++) {
            wrenches[i] = new Wrench();
        }

        // Mechaniker erzeugen und starten
        Mechanic[] mechanics = new Mechanic[Mechanic.N];
        for (int i = 0; i < Mechanic.N; i++) {
            mechanics[i] = new Mechanic(i, wrenches);
            mechanics[i].start();
        }

        // Auf Ende der Threads warten
        for (int i = 0; i < Mechanic.N; i++) {
            try {
                mechanics[i].join();
            } catch (InterruptedException e) { }
        }

        System.out.println("Fertig.");
    }
}