package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {
    Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.ROUNDS; i++) {
            try {
                int val = ThreadLocalRandom.current().nextInt(1, 100);
                System.out.println("PUT [" + Thread.currentThread().getName() + "]: " + val);
                buffer.put(val);
            } catch (InterruptedException e) { }
        }
    }
}
