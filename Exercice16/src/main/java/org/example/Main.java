package org.example;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 5, 2, 6, 5, 8, 7};
        int threadCount = 4;
        int splitList = numbers.length / threadCount;
        AtomicInteger totalSum = new AtomicInteger(0);

        CyclicBarrier barrier = new CyclicBarrier(threadCount, () -> {
            System.out.println("Somme totale : " + totalSum.get());
        });

        for (int i = 0; i < threadCount; i++) {
            final int threadIndex = i; // Capture de l'indice
            new Thread(() -> {
                int startIndex = threadIndex * splitList;
                int endIndex = (threadIndex == threadCount - 1) ? numbers.length : startIndex + splitList;

                int partialSum = 0;
                for (int j = startIndex; j < endIndex; j++) {
                    partialSum += numbers[j];
                }

                System.out.println(Thread.currentThread().getName() + " a calculé une somme partielle de (indice : "
                        + startIndex + " à indice : " + (endIndex - 1) + ") : " + partialSum);
                totalSum.addAndGet(partialSum);

                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Thread-" + (i + 1)).start();
        }
    }
}
