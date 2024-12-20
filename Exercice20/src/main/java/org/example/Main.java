package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {

    private static final int TASK_COUNTER = 10000;

    public static void main(String[] args) throws InterruptedException {
        long virtualTimer = TestVirtualThread();
        long threadTimer = TestThread();

        System.out.println("\n--- Résumé des performances ---");
        System.out.println("Threads natifs : " + threadTimer + " ms");
        System.out.println("Threads virtuels : " + virtualTimer + " ms");
    }

    private static void simpleTask() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static long TestVirtualThread() throws InterruptedException {
        long strTimer = System.currentTimeMillis();
        var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < TASK_COUNTER; i++) {
            executor.submit(Main::simpleTask);
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        long endTimer = System.currentTimeMillis();
        return endTimer - strTimer;
    }

    public static long TestThread() throws InterruptedException {
        long strtimer = System.currentTimeMillis();
        var executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < TASK_COUNTER; i++) {
            executor.submit(Main::simpleTask);
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endtimer = System.currentTimeMillis();
        return endtimer - strtimer;
    }
}