package org.example;

public class BankAccount {
    private Double balance;

    public BankAccount() {
        this.balance = 0.0;
    }

    public BankAccount(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public synchronized void deposit(int amount, String threadName) {
        balance += amount;
        System.out.println(threadName + " a déposé " + amount + ", solde actuel : " + balance);
    }

    public synchronized void withdraw(int amount, String threadName) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(threadName + " a retiré " + amount + ", solde actuel : " + balance);
        } else {
            System.out.println(threadName + " n'a pas pu retirer " + amount + " (solde insuffisant). Solde actuel : " + balance);
        }
    }
}
