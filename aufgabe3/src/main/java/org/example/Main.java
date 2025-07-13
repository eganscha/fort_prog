package org.example;

import static java.lang.Thread.sleep;

/************************************************************************

 Versuch: Thread-Synchronisation ueber Warteschleife.

 Ablauf:
 - Hauptthread startet Warte-Thread.
 - Dieser wartet in einer while-Schleife darauf, das "flag"
 "false" wird.
 - Haupt-Thread arbeitet (schlaeft 2 s) und signalisiert dann ueber
 "flag" den wartenden Thread.

 Funktioniert das so?

 ************************************************************************/
public class Main {
    public static void main(String[] args) {
        // Thread erzeugen und starten
        Bsp_01 my = new Bsp_01();
        my.start();

        // Wartet
        try {
            sleep(2000);
        } catch (InterruptedException e) { }

        // anderen Thread signalisieren
        System.out.println("Signalisiere Thread ...");
        my.flag = false;

        // fertig!
        System.out.println("Warte auf Beendigung des Threads");
        try {
            my.join();
        } catch (InterruptedException e) { }
    }
}