package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        List<Future<Integer>> futures = new ArrayList<>();

        try {
            for (int i = 1; i <= 10; i++) {
                int id = i;
                Callable<Integer> tache = () -> id * id;
                Future<Integer> future = executorService.submit(tache);
                futures.add(future);
            }
            
            for (int i = 1; i <= futures.size(); i++) {
                try {
                    int result = futures.get(i - 1).get();
                    System.out.println("Résultat de la tâche " + i + " : " + result);
                } catch (Exception e) {
                    System.out.println("Erreur lors de l'exécution de la tâche " + i);
                    e.printStackTrace();
                }
            }
        } finally {
            executorService.shutdown();
        }
    }
}