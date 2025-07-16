package org.example.counter;

public class Thread0 extends Counter {
    @Override
    public void run() {
        for(int i = 0; i < N; i++) {
            // Peterson
            flag0 = true; // I want to enter
            turn = 1; // But I'm humble and give T1 first entrance
            while (flag1 == true && turn == 1); // Busy waiting, while T1 is in its c.sec.
            // c.sec.
            increment();

            flag0 = false; // give c.sec. access to other T
        }
    }
}
