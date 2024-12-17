package org.example;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private static final AtomicLong atomicLong = new AtomicLong(1);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            int multiplier = i+2;
            threads[i] = new Thread(() -> {
                long result = multiplier * atomicLong.get();
                atomicLong.set(result);
                System.out.println(Thread.currentThread().getName() + " multiplicateur : " +multiplier+ " = " + result);
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        System.out.println("Valeur final " +atomicLong.get());
    }
}