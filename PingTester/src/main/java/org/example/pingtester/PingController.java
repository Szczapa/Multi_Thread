package org.example.pingtester;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PingController {

    Thread[] threads = new Thread[500];

    public  void start() throws InterruptedException {
        String sseUrl = "http://localhost:8080/connectsse";
        Thread sseThread = new Thread(() -> connectToSSE(sseUrl));

        for(int i=0; i<threads.length; i++){
            threads[i] = new Thread(() -> {connectToSSE(sseUrl);});
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

    public static void connectToSSE(String sseUrl) {
        HttpURLConnection connection = null;
        try {
            // Création de la connexion HTTP
            URL url = new URL(sseUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "text/event-stream");
            connection.setDoInput(true);


            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Erreur de connexion au serveur : " + responseCode);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                System.out.println("Connexion SSE établie. En attente des messages...");
                while ((line = reader.readLine()) != null) {

                    if (line.startsWith("data:")) {
                        String data = line.substring(5).trim();
                        System.out.println("Message reçu : " + data + " - " +Thread.currentThread().getName());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur dans la connexion SSE : " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
