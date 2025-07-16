package org.example;

import org.example.counter.Counter;
import org.example.counter.Thread0;
import org.example.counter.Thread1;

public class Main {
    public static void main(String[] args) {
        Thread0 thread0 = new Thread0();
        Thread1 thread1 = new Thread1();

        thread0.start();
        thread1.start();

        // Das Ende der beiden Threads mittels join() synchronisieren.
        try {
            thread0.join();
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("Join interrupted. Spurious event.");
        }

        // Ausgabe des Wertes
        System.out.println("val: " + thread0.val);
        // Always 4_000_000
    }
}