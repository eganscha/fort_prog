package org.example;

public class Main {
    private static final int SLEEP_DURATION_MS = 2_000;

    public static void main(String[] args) {
//        ablauf1();
        ablauf2();
    }

    public static void ablauf1() {
        Counter counter = new Counter();

        // Erst ruft der Haupt–Thread sum up(),
        counter.sum_up();

        // dann startet er den Thread.
        Thread thread = new Thread(counter);
        thread.start();

        // Primitives warten auf den Thread mittels .sleep, anstatt .join
        try {
            Thread.sleep(SLEEP_DURATION_MS);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted. Spurious event.");
        }

        // Ausgabe des Wertes
        System.out.println("Ablauf1:\nval: " + counter.val);
        // Ergebnis: Praktisch immer 4_000_000, weil wir zuerst 2_000_000 count aufrufen, bevor wir überhaupt mit der Parallelisierung beginnen.
        // (einzige potenzielle Fehlerquelle wäre, wenn 2s Wartezeit nicht reichen würden)
    }

    public static void ablauf2() {
        Counter counter = new Counter();

        // Zuerst startet er den Thread,
        Thread thread = new Thread(counter);
        thread.start();

        // dann ruft der Haupt–Thread sum up().
        counter.sum_up();

        // Primitives warten auf den Thread mittels .sleep, anstatt .join
        try {
            Thread.sleep(SLEEP_DURATION_MS);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted. Spurious event.");
        }

        // Ausgabe des Wertes
        System.out.println("Ablauf2:\nval: " + counter.val);
        // Ergebnis: Immer deutlich <4_000_000, weil wir systematisch lost updates produzieren, weil beide Threads parallel schreiben.
        // Mögliche Lösung: Atomic Int oder Methodenaufruf mithilfe von synchronized keyword.
    }
}