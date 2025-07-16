package org.example.counter;

public class Thread1 extends Counter {
    @Override
    public void run() {
        for(int i = 0; i < N; i++) {
            // Peterson
            flag1 = true; // I want to enter
            turn = 0; // But I'm humble and give T0 first entrance
            while (flag0 == true && turn == 0); // Busy waiting, while T0 is in its c.sec.
            // c.sec.
            increment();

            flag1 = false; // give c.sec. access to other T
        }
    }
}
