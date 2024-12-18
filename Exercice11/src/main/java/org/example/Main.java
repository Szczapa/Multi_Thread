package org.example;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bankAccount.withdraw(10, Thread.currentThread().getName());
            }
        }, "withdrawThread");

        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                bankAccount.deposit(10, Thread.currentThread().getName());
            }
        }, "depositThread");

        Thread depositThread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                bankAccount.deposit(10, Thread.currentThread().getName());
            }
        }, "depositThread2");

        withdrawThread.start();
        depositThread.start();
        depositThread2.start();

        try {
            withdrawThread.join();
            depositThread.join();
            depositThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Solde final : " + bankAccount.getBalance());

    }
}