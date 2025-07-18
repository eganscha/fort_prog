package org.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Mechanic extends Thread {
    private Semaphore[] wrenches;
    private int ms_sleepDurationWithoutWrench;
    private int ms_sleepDurationWithWrench;
    private int id;
    private boolean orderLeftWrenchFirst;

    public Mechanic(Semaphore[] wrenches, int id) {
        this.wrenches = wrenches;
        this.ms_sleepDurationWithoutWrench = ThreadLocalRandom.current().nextInt(1, 20);
        this.ms_sleepDurationWithWrench = ThreadLocalRandom.current().nextInt(1, 40);
        this.id = id;

        if (id % 2 == 0) {
            this.orderLeftWrenchFirst = true;
        } else {
            this.orderLeftWrenchFirst = false;
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.ROUNDS; i++) {
            try {
                // Work without Wrenches
                sleep(ms_sleepDurationWithoutWrench);

                System.out.println("[" + id + "] trying to acquire wrenches.");
                // Acquire Wrenches in particular order
                if (orderLeftWrenchFirst) {
                    wrenches[id].acquire();
                    wrenches[(id + 1) % Main.N].acquire();
                } else {
                    wrenches[(id + 1) % Main.N].acquire();
                    wrenches[id].acquire();
                }
                System.out.println("[" + id + "] successfully acquired wrenches.");

                // Work with Wrenches
                sleep(ms_sleepDurationWithWrench);

                // Put Wrenches Away (order doesn't matter)
                wrenches[id].release();
                wrenches[(id + 1) % Main.N].release();
            } catch (InterruptedException e) {}
        }
    }
}
