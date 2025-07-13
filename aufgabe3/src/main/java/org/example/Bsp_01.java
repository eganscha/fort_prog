package org.example;

class Bsp_01 extends Thread {

    // Fix:
    // public volatile boolean flag = true;

    // Bug:
    public boolean flag = true;

    // Thread-Code
    public void run() {
        System.out.println("> Start Thread");

        // busy waiting
        while (flag) {
            /* wait */
        };

        System.out.println("> Ende Thread");
    }
}