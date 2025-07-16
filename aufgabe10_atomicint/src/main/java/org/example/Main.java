package org.example;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        // Der Haupt–Thread startet den anderen Thread,
        Thread thread = new Thread(counter);
        thread.start();

        // dann ruft er selbst sum up().
        counter.sum_up();

        // Das Ende der beiden Threads mittels join() synchronisieren.
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Join interrupted. Spurious event.");
        }

        // Ausgabe des Wertes
        System.out.println("val: " + counter.getValue());
    }
}