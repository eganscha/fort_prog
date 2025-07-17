package org.example;

import java.util.ArrayList;

public class Buffer {
    private ArrayList<Character> buffer;
    private int size;
    private int placed;

    public Buffer(int size) {
        buffer = new ArrayList<Character>();
        this.size = size;
        placed = 0;
    }

    public synchronized void put(char c) {
        while (placed >= size) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        buffer.add(c);
        placed++;
        notifyAll();
    }

    public synchronized char get() {
        while (placed < 1) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        char val = buffer.remove(0);
        placed--;
        notifyAll();
        return val;
    }
}
