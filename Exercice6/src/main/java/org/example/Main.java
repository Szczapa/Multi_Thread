package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.schedule(() -> {
            System.out.println("Tâche 1 exécutée après 1 seconde.");
        }, 1, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            System.out.println("Tâche 2 exécutée après 2 secondes.");
        }, 2, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            System.out.println("Tâche 3 exécutée après 3 secondes.");
        }, 3, TimeUnit.SECONDS);


        scheduler.schedule(() -> {
            System.out.println("Toutes les tâches ont été exécutées. Arrêt du programme.");
            scheduler.shutdown();
        }, 4, TimeUnit.SECONDS);
    }
}