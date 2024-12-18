package org.example;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        int threadCount = 3; // Nombre de threads
        int totalSteps = 3; // Nombre total d'étapes

        CyclicBarrier barrier = new CyclicBarrier(threadCount, () -> {
            // Cette action est exécutée à la fin de chaque étape
            System.out.println("Tous les threads ont atteint la barrière pour cette étape !");
        });

        Runnable task = () -> {
            try {
                for (int step = 1; step <= totalSteps; step++) {

                    System.out.println(Thread.currentThread().getName() + " atteint l'étape " + step);
                    barrier.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < threadCount; i++) {
            new Thread(task, "Thread-" + (i + 1)).start();
        }
    }
}
