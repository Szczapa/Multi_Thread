package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        AtomicInteger counter = new AtomicInteger(0);

        service.scheduleAtFixedRate(() -> {
            int currentCount = counter.incrementAndGet();
            System.out.println("Message périodique " + currentCount);

            if (currentCount >= 5) {
                System.out.println("Programme terminé.");
                service.shutdown();
            }
        }, 1, 2, TimeUnit.SECONDS);
    }
}