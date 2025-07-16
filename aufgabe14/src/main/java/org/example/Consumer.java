package org.example;

public class Consumer extends Thread {
    Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.ROUNDS; i++) {
            try {
                int val = buffer.get();
                System.out.println("GET [" + Thread.currentThread().getName() + "]: " + val);
            } catch (InterruptedException e) { }
        }
    }
}
