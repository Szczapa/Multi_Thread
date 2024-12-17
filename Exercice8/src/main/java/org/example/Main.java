package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int threadCounter = 5;
        Thread[] threads = new Thread[threadCounter];



        for (int i = 0; i < threadCounter; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicInteger.incrementAndGet();
                }
            });
        }
        for(Thread thread : threads) thread.start();
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(atomicInteger.get());
    }
}