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
        System.out.println("Ablauf1:\nval: " + counter.val);
        // Ergebnis: Weiterhin <4_000_000. Nur, weil wir auf die ordentliche Beendigung des anderen Threads wartet bedeutet das nicht,
        // dass wir die provozierten lost updates irgendwie behoben hätten.
        // Lösung: Weiterhin AtomicInteger, oder synchronized
    }
}