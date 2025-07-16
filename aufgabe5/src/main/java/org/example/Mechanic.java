package org.example;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class Mechanic extends Thread {
    private int id;
    private Wrench[] wrenches;
    private int currentRound = 0;

    public static final int N = 10; // Num of Mechanics, Wrenches
    private static final int ROUNDS = 5000;
    private static final int MAX_SLEEP_DURATION_WITHOUT_WRENCH = 1;
    private static final int MAX_SLEEP_DURATION_WITH_WRENCH = 3;

    public Mechanic(int id, Wrench[] wrenches) {
        this.id = id;
        this.wrenches = wrenches;

        ThreadLocalRandom.current();
    }

    public String generateStatusString(String status) {
        StringBuilder sb = new StringBuilder();

        sb.append("timestamp: " + LocalDateTime.now() + "\t");
        sb.append("current-round: " + currentRound + "\t");
        sb.append("id: " + id + "\t");
        sb.append("status: " + status + "\t");

        return sb.toString();
    }

    @Override
    public void run() {
        System.out.println(generateStatusString(""));

        // Hier wäre ein guter Platz für eine Barriere

        // Zufällige (nicht gleichverteilte) Wartezeiten
        int sleepDurationWithoutWrench = ThreadLocalRandom.current().nextInt(0, MAX_SLEEP_DURATION_WITHOUT_WRENCH);
        int sleepDurationWithWrench  = ThreadLocalRandom.current().nextInt(0, MAX_SLEEP_DURATION_WITH_WRENCH);

        for (currentRound = 0; currentRound < ROUNDS; ++currentRound) {
            // Ohne Wrenches arbeiten
            try {
                Thread.sleep(MAX_SLEEP_DURATION_WITHOUT_WRENCH);
            } catch (InterruptedException e) { }

            // Wrenches aufheben
            System.out.println(generateStatusString("take left wrench"));
            wrenches[id].take();

            System.out.println(generateStatusString("take right wrench"));
            wrenches[(id+1) % N].take();

            // Mit Wrenches arbeiten
            try {
                Thread.sleep(MAX_SLEEP_DURATION_WITH_WRENCH);
            } catch (InterruptedException e) { }

            // Wrenches ablegen
            System.out.println(generateStatusString("put left wrench"));
            wrenches[id].put();

            System.out.println(generateStatusString("put right wrench"));
            wrenches[(id+1) % N].put();
        }
    }
}
