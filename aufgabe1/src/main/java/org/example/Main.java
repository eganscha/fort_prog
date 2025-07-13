package org.example;

public class Main {
    private static final int MAIN_THREAD_SLEEP_MS = 500;
    private static final int THREAD_COUNT = 5;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Worker());
        }

        // Vor dem Start
        for (int i = 0; i < THREAD_COUNT; i++) {
            printThreadStatus(threads[i]);
        }

        // Starte Thread und gebe sofort seinen Status aus
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].start();
            printThreadStatus(threads[i]);
            try {
                Thread.sleep(MAIN_THREAD_SLEEP_MS);
            } catch (InterruptedException e) {
                // EMPTY
            }
        }
    }

    private static void printThreadStatus(Thread t) {
        System.out.println("Thread status:");
        System.out.println("Name: " + t.getName());
        System.out.println("ID: " + t.getId());
        System.out.println("isAlive: " + t.isAlive() + "\n");
    }
}

/*
Beispielausgabe (Lange Wartezeiten):

    Thread status:
    Name: Thread-0
    ID: 13
    isAlive: false

    Thread status:
    Name: Thread-1
    ID: 14
    isAlive: false

    Thread status:
    Name: Thread-2
    ID: 15
    isAlive: false

    Thread status:
    Name: Thread-3
    ID: 16
    isAlive: false

    Thread status:
    Name: Thread-4
    ID: 17
    isAlive: false

    Thread status:
    Name: Thread-0
    ID: 13
    isAlive: true

    Thread status:
    Name: Thread-1
    ID: 14
    isAlive: true

    Hello from Thread: Thread-0
    Thread status:
    Name: Thread-2
    ID: 15
    isAlive: true

    Hello from Thread: Thread-1
    Thread status:
    Name: Thread-3
    ID: 16
    isAlive: true

    Hello from Thread: Thread-2
    Thread status:
    Name: Thread-4
    ID: 17
    isAlive: true

    Hello from Thread: Thread-3
    Hello from Thread: Thread-4

    Process finished with exit code 0

------------------------------------------------------------------------

Beispielausgabe (Kurze Wartezeiten):

    Thread status:
    Name: Thread-0
    ID: 13
    isAlive: false

    Thread status:
    Name: Thread-1
    ID: 14
    isAlive: false

    Thread status:
    Name: Thread-2
    ID: 15
    isAlive: false

    Thread status:
    Name: Thread-3
    ID: 16
    isAlive: false

    Thread status:
    Name: Thread-4
    ID: 17
    isAlive: false

    Thread status:
    Name: Thread-0
    ID: 13
    isAlive: true

    Thread status:
    Name: Thread-1
    ID: 14
    isAlive: true

    Hello from Thread: Thread-0
    Thread status:
    Name: Thread-2
    ID: 15
    isAlive: true

    Hello from Thread: Thread-1
    Thread status:
    Name: Thread-3
    ID: 16
    isAlive: true

    Hello from Thread: Thread-2
    Thread status:
    Name: Thread-4
    ID: 17
    isAlive: true

    Hello from Thread: Thread-3
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