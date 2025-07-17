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
            String alphabet = "abcdefghaijklmnopqrstuvwxyz";
            char val = alphabet.charAt(ThreadLocalRandom.current().nextInt(0, 25));
            System.out.println("PUT [" + Thread.currentThread().getName() + "]: " + val);
            buffer.put(val);
        }
    }
}