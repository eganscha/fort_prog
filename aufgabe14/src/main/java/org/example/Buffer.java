package org.example;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Buffer {
    private ArrayList<Integer> buffer;
    private Semaphore remaining;
    private Semaphore used;

    public Buffer(int size) {
        buffer = new ArrayList<>(size);
        remaining = new Semaphore(size);
        used = new Semaphore(0);
    }

    public void put(int val) throws InterruptedException {
        remaining.acquire();
        synchronized (this) {
            buffer.add(val);
            used.release();
        }
    }

    public int get() throws InterruptedException {
        used.acquire();
        synchronized (this) {
            int val = buffer.remove(0);
            remaining.release();
            return val;
        }
    }
}
