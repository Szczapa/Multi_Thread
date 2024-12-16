package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {

            for (int i = 1; i <= 10; i++) {
                int idTache = i;
                executorService.submit(() -> {
                    System.out.println("Tâche " + idTache + " exécutée par " + Thread.currentThread().getName());
                });
            }
        } finally {
            executorService.shutdown();
        }
    }
}