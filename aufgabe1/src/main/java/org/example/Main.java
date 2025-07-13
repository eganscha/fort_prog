package org.example;

public class Main {
    private static final int MAIN_THREAD_SLEEP_MS = 50;
    private static final int THREAD_COUNT = 5;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Worker());
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            System.out.println("Name: " + threads[i].getName());
            System.out.println("ID: " + threads[i].getId());
            System.out.println("isAlive: " + threads[i].isAlive() + "\n");
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].start();
            try {
                Thread.sleep(MAIN_THREAD_SLEEP_MS);
            } catch (InterruptedException e) {
                // EMPTY
            }
        }

        System.out.println("\n");
        for (int i = 0; i < THREAD_COUNT; i++) {
            System.out.println("Name: " + threads[i].getName());
            System.out.println("ID: " + threads[i].getId());
            System.out.println("isAlive: " + threads[i].isAlive() + "\n");
        }
    }
}

/*
Beispielausgabe (Lange Wartezeiten):

    Name: Thread-0
    ID: 13
    isAlive: false

    Name: Thread-1
    ID: 14
    isAlive: false

    Name: Thread-2
    ID: 15
    isAlive: false

    Name: Thread-3
    ID: 16
    isAlive: false

    Name: Thread-4
    ID: 17
    isAlive: false

    Hello from Thread: Thread-0
    Hello from Thread: Thread-1
    Hello from Thread: Thread-2
    Hello from Thread: Thread-3
    Hello from Thread: Thread-4


    Name: Thread-0
    ID: 13
    isAlive: false

    Name: Thread-1
    ID: 14
    isAlive: false

    Name: Thread-2
    ID: 15
    isAlive: false

    Name: Thread-3
    ID: 16
    isAlive: false

    Name: Thread-4
    ID: 17
    isAlive: false

    Process finished with exit code 0

------------------------------------------------------------------------

Beispielausgabe (Kurze Wartezeiten):

    Name: Thread-0
    ID: 13
    isAlive: false

    Name: Thread-1
    ID: 14
    isAlive: false

    Name: Thread-2
    ID: 15
    isAlive: false

    Name: Thread-3
    ID: 16
    isAlive: false

    Name: Thread-4
    ID: 17
    isAlive: false

    Hello from Thread: Thread-0
    Hello from Thread: Thread-1
    Hello from Thread: Thread-2


    Hello from Thread: Thread-3
    Name: Thread-0
    ID: 13
    isAlive: false

    Name: Thread-1
    ID: 14
    isAlive: false

    Name: Thread-2
    ID: 15
    isAlive: false

    Name: Thread-3
    ID: 16
    isAlive: false

    Name: Thread-4
    ID: 17
    isAlive: true

    Hello from Thread: Thread-4

    Process finished with exit code 0

Sinn der Aufgabe:
    Typische Aha-Effekte:
        1.	„Ich drucke direkt nach start() – trotzdem ist der Thread manchmal schon fertig!“
            → Erkenntnis: Ein laufender Thread kann in wenigen Hundert Taktzyklen sein run() abarbeiten.
        2.	„Ändere ich nur eine Schlafzeit um 100 ms, sieht der gesamte Output anders aus.“
            → Erkenntnis: Nebenläufigkeit ist empfindlich; Tests mit hartkodierten Sleeps sind fragil.
        3.	„isAlive() liefert nicht immer, was ich erwarte.“
            → Erkenntnis: Ohne echte Synchronisation (z. B. join()) bekommen Sie lediglich Schnappschüsse.
 */