package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        // Créer un exécuteur à thread unique
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        try {
            // Soumettre des tâches pour les nombres de 1 à 5
            Future<String>[] results = new Future[5];
            for (int i = 1; i <= 5; i++) {
                MyCallable tache = new MyCallable(i);
                results[i - 1] = executorService.submit(tache);
            }

            // Récupérer et afficher les résultats
            for (Future<String> result : results) {
                System.out.println(result.get()); // Bloc jusqu'à ce que la tâche soit terminée
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Arrêter l'exécuteur proprement
            executorService.shutdown();
        }
    }
}
