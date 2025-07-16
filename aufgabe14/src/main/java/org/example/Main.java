package org.example;

public class Main {
    public static final int ROUNDS = 100;

    private static final int BUFFER_SIZE = 5;
    private static final int NUM_PROD_CONS = 10;

    public static void main(String[] args) {
        Buffer buffer = new Buffer(BUFFER_SIZE);
        Producer[]  producers = new Producer[NUM_PROD_CONS];
        Consumer[] consumers = new Consumer[NUM_PROD_CONS];

        // Create and start producers / consumers
        for(int i = 0; i < NUM_PROD_CONS; i++) {
            producers[i] = new Producer(buffer);
            producers[i].start();

            consumers[i] = new Consumer(buffer);
            consumers[i].start();
        }

        // Wait for them to join
        for (int i = 0; i < NUM_PROD_CONS; i++) {
            try {
                producers[i].join();
                consumers[i].join();
            } catch (InterruptedException e) { }
        }

        System.out.println("Done.");
    }
}