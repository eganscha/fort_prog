package org.example;

import java.util.ArrayList;

public class Buffer {
    private ArrayList<Character> buffer;
    private int capacity;

    public Buffer(int capacity) {
        buffer = new ArrayList<Character>(capacity);
        this.capacity = capacity;
    }

    public synchronized void put(char c) {
        while (buffer.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        buffer.add(c);
        notifyAll();
    }

    public synchronized char get() {
        while (buffer.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        char val = buffer.remove(0);
        notifyAll();
        return val;
    }
}
