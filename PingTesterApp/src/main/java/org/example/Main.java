package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private final Thread[] threads = new Thread[10000];
    public Main() {

        for (int i = 0; i < threads.length; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> pingServer(threadId));
        }
    }

    public static void main(String[] args) {
        Main app = new Main(); // Créer une instance de la classe Main
        try {
            app.start(); // Démarrer les threads
        } catch (InterruptedException e) {
            System.err.println("Le programme a été interrompu : " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void start() throws InterruptedException {
        System.out.println("Démarrage des threads...");

        for (Thread thread : threads) thread.start();

        for (Thread thread : threads) thread.join();
        System.out.println("Tous les threads ont terminé.");
    }

    private void pingServer(int threadId) {
        try {
            System.out.println("Thread " + threadId + " commence à pinger...");


            URL url = new URL("http://localhost:8080/connect");


            synchronized (Main.class) {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");


                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    // Lire le corps de la réponse (JSON) depuis l'InputStream
                    String jsonResponse = new String(connection.getInputStream().readAllBytes());
                    System.out.println("Réponse JSON : " + jsonResponse);
                } else {
                    System.out.println("Erreur HTTP : " + responseCode + " - " + connection.getResponseMessage());
                }


                connection.disconnect();
            }

        } catch (IOException e) {
            System.err.println("Erreur dans le thread " + threadId + " : " + e.getMessage());
        }
    }
}
