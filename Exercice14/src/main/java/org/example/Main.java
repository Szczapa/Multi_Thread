package org.example;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Fusion des données terminée. Tous les threads peuvent continuer.");
        });

        Runnable task = () -> {
            try {
                // Simule le chargement de données
                System.out.println(Thread.currentThread().getName() + " commence à charger les données.");
                Thread.sleep((long) (Math.random() * 3000));
                System.out.println(Thread.currentThread().getName() + " a terminé le chargement des données.");

                // Attend les autres threads à la barrière
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(task, "Thread-" + (i + 1)).start();
        }
    }
}