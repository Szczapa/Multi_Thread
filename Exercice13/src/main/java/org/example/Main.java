package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        System.out.println("Début du programme...\n");

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " tente d'obtenir le verrou avec lock().");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " a acquis le verrou et exécute la tâche.");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " a été interrompu.");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " a libéré le verrou.");
            }
        }, "User1");


        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " tente d'obtenir le verrou avec tryLock().");
            if (lock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " a acquis le verrou et exécute la tâche.");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " a été interrompu.");
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " a libéré le verrou.");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " n'a pas pu obtenir le verrou (verrou occupé).");
            }
        }, "User2");

            Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " tente d'obtenir le verrou avec tryLock(timeout).");
            try {
                Thread.sleep(500);
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " a acquis le verrou et exécute la tâche.");
                        Thread.sleep(1500);
                    } finally {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName() + " a libéré le verrou.");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " n'a pas pu obtenir le verrou après attente.");
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " a été interrompu pendant l'attente.");
            }
        }, "User3");


        t1.start();
        t2.start();
        t3.start();


        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread a été interrompu.");
        }

        System.out.println("\nFin du programme.");
    }
}
