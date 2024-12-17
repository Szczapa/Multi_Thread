package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int currentValue = atomicInteger.get();
                if (currentValue < 10) {

                    atomicInteger.compareAndSet(currentValue, currentValue + 1);
                }
            });
        }
        for(Thread thread : threads) thread.start();
        for(Thread thread : threads) thread.join();
        System.out.println("Valeur finale du compteur : " + atomicInteger.get());

    }
}