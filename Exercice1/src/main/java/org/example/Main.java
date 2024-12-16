package org.example;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
        new MyThread().start();
        }
        System.out.println("Thread Termine");
    }
}